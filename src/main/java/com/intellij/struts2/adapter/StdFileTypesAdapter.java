package com.intellij.struts2.adapter;

import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.fileTypes.LanguageFileType;

public class StdFileTypesAdapter {
    public static volatile LanguageFileType JSP = (LanguageFileType) FileTypeManager.getInstance().getStdFileType("JSP");
    public static volatile LanguageFileType JSPX = (LanguageFileType)FileTypeManager.getInstance().getStdFileType("JSPX");
}
