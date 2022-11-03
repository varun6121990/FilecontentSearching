package com.file.content.searching.service.defn;

import java.io.IOException;

@FunctionalInterface
public interface ReadFileContent {

	public void getStringPositionDetailsForInputText(String filePathWithFileName) throws IOException;
}
