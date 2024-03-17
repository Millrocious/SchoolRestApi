package com.endropioz.schoolrestapp.csvutil.excepion.excel;

public class UnsupportedExcelFormatException extends IllegalArgumentException {
    public UnsupportedExcelFormatException(String s) {
        super(s);
    }
}
