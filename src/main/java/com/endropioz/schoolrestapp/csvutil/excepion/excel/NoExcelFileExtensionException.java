package com.endropioz.schoolrestapp.csvutil.excepion.excel;

public class NoExcelFileExtensionException extends IllegalArgumentException {
    public NoExcelFileExtensionException(String s) {
        super(s);
    }

    public NoExcelFileExtensionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoExcelFileExtensionException(Throwable cause) {
        super(cause);
    }
}
