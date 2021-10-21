package com.example.test_pay.service.ServiceImpl;

import com.example.test_pay.entity.QuestionEntity;
import com.example.test_pay.entity.TestEntity;
import com.example.test_pay.entity.User;
import com.example.test_pay.repository.QuestionRepository;
import com.example.test_pay.repository.TestRepository;
import com.example.test_pay.repository.UserRepository;
import com.example.test_pay.service.ParseDataService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParseDataServiceImpl implements ParseDataService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRepository testRepository;


    @Override
    public ResponseEntity<?> excelExport(String type) throws IOException {
        List<String> headColumn = new ArrayList<>();
        headColumn.add("id");
        List<?> entities = null;

        if (type.equals("question")){
            headColumn.add("Savollar");
            headColumn.add("Variant-A");
            headColumn.add("Variant-B");
            headColumn.add("Variant-C");
            headColumn.add("To'g'ri javob");
            headColumn.add("Test id");
            entities = questionRepository.findAll();
        }

        if (type.equals("users")){
            headColumn.add("Role");
            headColumn.add("Balance");
            headColumn.add("Password");
            headColumn.add("Name");
            headColumn.add("Telefon No'mer");
            entities = userRepository.findAll();
        }



        if (type.equals("tests")){
            headColumn.add("Narx");
            headColumn.add("Teacher id");
            headColumn.add("Sarlavha");
            entities = testRepository.findAll();
        }



        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(type);

        writeHeaderLine(sheet,headColumn,0);

        writeHeaderLine(entities,workbook, sheet, type);
        FileOutputStream outputStream = new FileOutputStream("export.xlsx");
        workbook.write(outputStream);
        workbook.close();



        return null;
    }

    private void writeHeaderLine(XSSFSheet sheet, List<String> headColumn, int index) {

        Row headerRow = sheet.createRow(index);

        for (int i=0; i<headColumn.size(); i++){
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(headColumn.get(i));
        }
    }

    private void writeHeaderLine(List<?> entities, XSSFWorkbook workbook, XSSFSheet sheet, String type) {

        for (int i=0; i<entities.size(); i++){
            List<String> s = convertString(entities.get(i),type,i);
            writeHeaderLine(sheet,s,i+1);
        }
    }

    private List<String> convertString(Object o, String type, int i) {
        List<String> list = new ArrayList<>();
        if (type.equals("question")){
            QuestionEntity entity = new QuestionEntity();
            BeanUtils.copyProperties(o,entity);
            list.add(String.valueOf(i));
            list.add(entity.getText());
            list.add(entity.getAnsA());
            list.add(entity.getAnsB());
            list.add(entity.getAnsC());
            list.add(entity.getCurrentAnswer().toString());
            list.add(String.valueOf(entity.getTest_id()));
        }

        if (type.equals("users")){
            User entity = new User();
            BeanUtils.copyProperties(o,entity);
            list.add(String.valueOf(i));
            list.add(String.valueOf(entity.getRole()));
            list.add(String.valueOf(entity.getBalance()));
            list.add(entity.getPassword());
            list.add(entity.getPhone());
        }


        if (type.equals("tests")){
            TestEntity entity = new TestEntity();
            BeanUtils.copyProperties(o,entity);
            list.add(String.valueOf(i));
            list.add(entity.getPrice().toString());
            list.add(entity.getTitle());
        }

        return list;
    }

}
