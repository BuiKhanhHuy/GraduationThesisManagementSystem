package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.*;
import com.buikhanhhuy.service.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import utils.ExcelFileUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReadExcelServiceImplement implements ReadExcelService {
    @Autowired
    private MajorService majorService;
    @Autowired
    private SchoolYearService schoolYearService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private RoleService roleService;

    public static final int COLUMN_STUDENT_INDEX_CODE = 0;
    public static final int COLUMN_STUDENT_INDEX_FULL_NAME = 1;
    public static final int COLUMN_STUDENT_INDEX_EMAIL = 2;
    public static final int COLUMN_STUDENT_INDEX_PHONE_NUMBER = 3;
    public static final int COLUMN_STUDENT_INDEX_BIRTHDAY = 4;
    public static final int COLUMN_STUDENT_INDEX_GENDER = 5;
    public static final int COLUMN_STUDENT_INDEX_ADDRESS = 6;
    public static final int COLUMN_STUDENT_INDEX_GPA = 7;
    public static final int COLUMN_STUDENT_INDEX_SCHOOL_YEAR = 8;
    public static final int COLUMN_STUDENT_INDEX_MAJOR = 9;
    public static final int COLUMN_STUDENT_INDEX_IMAGE_URL = 10;

    public static final int COLUMN_LECTURER_INDEX_CODE = 0;
    public static final int COLUMN_LECTURER_INDEX_FULL_NAME = 1;
    public static final int COLUMN_LECTURER_INDEX_EMAIL = 2;
    public static final int COLUMN_LECTURER_INDEX_PHONE_NUMBER = 3;
    public static final int COLUMN_LECTURER_INDEX_BIRTHDAY = 4;
    public static final int COLUMN_LECTURER_INDEX_GENDER = 5;
    public static final int COLUMN_LECTURER_INDEX_ADDRESS = 6;
    public static final int COLUMN_LECTURER_INDEX_POSITION = 7;
    public static final int COLUMN_LECTURER_INDEX_DEPARTMENT = 8;
    public static final int COLUMN_LECTURER_INDEX_ROLE = 9;
    public static final int COLUMN_LECTURER_INDEX_IMAGE_URL = 10;

    @Override
    public List<Student> getStudentsFromFile(MultipartFile file) {
        List<Student> students = new ArrayList<>();
        try {

            // Get workbook
            Workbook workbook = ExcelFileUtils.getWorkbook(file.getInputStream(), Objects.requireNonNull(file.getOriginalFilename()));
            // Get sheet
            Sheet sheet = workbook.getSheetAt(0);
            // Get all rows
            for (Row nextRow : sheet) {
                if (nextRow.getRowNum() == 0) {
                    // Ignore header
                    continue;
                }

                // Get all cells
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                // Read cells and set value for user object
                User user = new User();
                Student student = new Student();
                while (cellIterator.hasNext()) {
                    //Read cell
                    Cell cell = cellIterator.next();
                    Object cellValue = ExcelFileUtils.getCellValue(cell);
                    if (cellValue == null || cellValue.toString().isEmpty()) {
                        throw new Exception("Incorrect data format");
                    }
                    // Set value for user object
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case COLUMN_STUDENT_INDEX_CODE:
                            student.setCode((String) ExcelFileUtils.getCellValue(cell));
                            user.setUsername((String) ExcelFileUtils.getCellValue(cell));
                            user.setPassword((String) ExcelFileUtils.getCellValue(cell));
                            break;
                        case COLUMN_STUDENT_INDEX_FULL_NAME:
                            student.setFullName(Objects.toString(ExcelFileUtils.getCellValue(cell)));
                            break;
                        case COLUMN_STUDENT_INDEX_EMAIL:
                            student.setEmail((String) ExcelFileUtils.getCellValue(cell));
                            break;
                        case COLUMN_STUDENT_INDEX_PHONE_NUMBER:
                            student.setPhone(String.valueOf(ExcelFileUtils.getCellValue(cell).toString()));
                            break;
                        case COLUMN_STUDENT_INDEX_BIRTHDAY:
                            student.setBirthday(new Date());
                            break;
                        case COLUMN_STUDENT_INDEX_GENDER:
                            student.setGender((int) Double.parseDouble(ExcelFileUtils.getCellValue(cell).toString()));
                            break;
                        case COLUMN_STUDENT_INDEX_ADDRESS:
                            student.setAddress((String) ExcelFileUtils.getCellValue(cell));
                            break;
                        case COLUMN_STUDENT_INDEX_GPA:
                            student.setGpa(Double.parseDouble(ExcelFileUtils.getCellValue(cell).toString()));
                            break;
                        case COLUMN_STUDENT_INDEX_SCHOOL_YEAR:
                            student.setSchoolYear(this.schoolYearService.getSchoolYearById((int) Double.parseDouble(ExcelFileUtils.getCellValue(cell).toString())));
                            break;
                        case COLUMN_STUDENT_INDEX_MAJOR:
                            student.setMajor(this.majorService.getMajorById((int) Double.parseDouble(ExcelFileUtils.getCellValue(cell).toString())));
                            break;
                        case COLUMN_STUDENT_INDEX_IMAGE_URL:
                            user.setAvatar((String) ExcelFileUtils.getCellValue(cell));
                        default:
                            break;
                    }

                }
                student.setUser(user);
                students.add(student);
            }
            workbook.close();

            return students;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return students;
    }

    @Override
    public List<Lecturer> getLecturersFromFile(MultipartFile file) {
        List<Lecturer> lecturers = new ArrayList<>();
        try {

            // Get workbook
            Workbook workbook = ExcelFileUtils.getWorkbook(file.getInputStream(), Objects.requireNonNull(file.getOriginalFilename()));
            // Get sheet
            Sheet sheet = workbook.getSheetAt(0);
            // Get all rows
            for (Row nextRow : sheet) {
                if (nextRow.getRowNum() == 0) {
                    // Ignore header
                    continue;
                }

                // Get all cells
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                // Read cells and set value for user object
                User user = new User();
                Lecturer lecturer = new Lecturer();
                while (cellIterator.hasNext()) {
                    //Read cell
                    Cell cell = cellIterator.next();
                    Object cellValue = ExcelFileUtils.getCellValue(cell);
                    if (cellValue == null || cellValue.toString().isEmpty()) {
                        throw new Exception("Incorrect data format");
                    }
                    // Set value for user object
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case COLUMN_LECTURER_INDEX_CODE:
                            lecturer.setCode((String) ExcelFileUtils.getCellValue(cell));
                            user.setUsername((String) ExcelFileUtils.getCellValue(cell));
                            user.setPassword((String) ExcelFileUtils.getCellValue(cell));
                            break;
                        case COLUMN_LECTURER_INDEX_FULL_NAME:
                            lecturer.setFullName((String) ExcelFileUtils.getCellValue(cell));
                            break;
                        case COLUMN_LECTURER_INDEX_EMAIL:
                            lecturer.setEmail((String) ExcelFileUtils.getCellValue(cell));
                            break;
                        case COLUMN_LECTURER_INDEX_PHONE_NUMBER:
                            lecturer.setPhone(String.valueOf(ExcelFileUtils.getCellValue(cell).toString()));
                            break;
                        case COLUMN_LECTURER_INDEX_BIRTHDAY:
                            lecturer.setBirthday(new Date());
                            break;
                        case COLUMN_LECTURER_INDEX_GENDER:
                            lecturer.setGender((int) Double.parseDouble(ExcelFileUtils.getCellValue(cell).toString()));
                            break;
                        case COLUMN_LECTURER_INDEX_ADDRESS:
                            lecturer.setAddress((String) ExcelFileUtils.getCellValue(cell));
                            break;
                        case COLUMN_LECTURER_INDEX_POSITION:
                            lecturer.setPosition(this.positionService.getPositionById((int) Double.parseDouble(ExcelFileUtils.getCellValue(cell).toString())));
                            break;
                        case COLUMN_LECTURER_INDEX_DEPARTMENT:
                            lecturer.setDepartment(this.departmentService.getDepartmentById((int) Double.parseDouble(ExcelFileUtils.getCellValue(cell).toString())));
                            break;
                        case COLUMN_LECTURER_INDEX_ROLE:
                            user.setRole(this.roleService.getRoleByRoleName(String.valueOf(ExcelFileUtils.getCellValue(cell).toString())));
                            break;
                        case COLUMN_LECTURER_INDEX_IMAGE_URL:
                            user.setAvatar((String) ExcelFileUtils.getCellValue(cell));
                        default:
                            break;
                    }

                }
                lecturer.setUser(user);
                lecturers.add(lecturer);
            }
            workbook.close();

            return lecturers;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return lecturers;
    }
}
