package com.endropioz.schoolrestapp.csvutil.util;

import com.endropioz.schoolrestapp.csvutil.excepion.excel.EmptyExcelFileException;
import com.endropioz.schoolrestapp.csvutil.excepion.excel.ExcelFileProcessingException;
import com.endropioz.schoolrestapp.csvutil.excepion.excel.NoExcelFileExtensionException;
import com.endropioz.schoolrestapp.csvutil.excepion.excel.UnsupportedExcelFormatException;
import com.endropioz.schoolrestapp.csvutil.validation.ValidationUtil;
import com.endropioz.schoolrestapp.csvutil.validation.validator.Validator;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@UtilityClass
public class ExcelUtil {
    public <T> List<T> excelDataToEntityList(MultipartFile file, Class<T> entityType, Validator<T> validator) {
        List<T> entities;
        validateExcelFile(file);

        try {
            PoijiExcelType excelType = getPoijiExcelType(file).get();
            entities = Poiji.fromExcel(file.getInputStream(), excelType, entityType);
        } catch (Exception e) {
            String errorMsg = "Error processing the uploaded Excel file!";
            log.error(errorMsg, e);
            throw new ExcelFileProcessingException(errorMsg, e);
        }

        return ValidationUtil.validateData(entities, validator);
    }

    private void validateExcelFile(MultipartFile file) {
        String errorMsg;
        if (file.isEmpty()) {
            errorMsg = String.format("The provided file %s is empty!", file.getOriginalFilename());
            log.error(errorMsg);
            throw new EmptyExcelFileException(errorMsg);
        }

        Optional<PoijiExcelType> poijiExcelType = getPoijiExcelType(file);
        if (poijiExcelType.isEmpty()) {
            errorMsg = String.format("%s file format is not supported!", getFileExtension(file).toUpperCase());
            log.error(errorMsg);
            throw new UnsupportedExcelFormatException(errorMsg);
        }
    }

    private Optional<PoijiExcelType> getPoijiExcelType(MultipartFile file) {
        return Arrays.stream(PoijiExcelType.values())
                .filter(e -> e.name().equalsIgnoreCase(getFileExtension(file)))
                .findFirst();
    }

    private String getFileExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        if (!Objects.isNull(fileName) && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            String errorMsg = String.format("Invalid file %s: The provided file doesn't have an extension. Please upload an Excel file with an '.xls' or '.xlsx' extension!", file.getOriginalFilename());
            log.error(errorMsg);
            throw new NoExcelFileExtensionException(errorMsg);
        }
    }
}
