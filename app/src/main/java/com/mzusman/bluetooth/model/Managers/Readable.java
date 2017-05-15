package com.mzusman.bluetooth.model.Managers;

import java.io.IOException;
import java.util.List;


public interface Readable {
    List<String> getReadings() throws IOException;

    String getReading(String READ);
}
