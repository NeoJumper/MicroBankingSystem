package com.kcc.banking;

import com.kcc.banking.domain.BulkTransferPreview;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("test", "test");
        return "index";
    }

    @GetMapping("/page/error/error-404")
    public String error404(Model model) {
        return "error-404";
    }

    @GetMapping("/page/error/error-403")
    public String error403(Model model) {
        return "error-403";
    }

    @GetMapping("/admin/employee-management")
    public String employeeManagement(Model model) {

        return "employee-management";
    }

}
