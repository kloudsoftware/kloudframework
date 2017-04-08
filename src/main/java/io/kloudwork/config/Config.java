package io.kloudwork.config;

/*
 * Config.java - Provides app-wide configuration.
 *
 * Copyright 2017 kloud.software
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

import io.kloudwork.helper.IOHelper;

import java.io.*;
import java.util.Properties;

public class Config {

    private static File configFileDir = new java.io.File("config");
    private static File configFile = new java.io.File(configFileDir, Constants.CONFIG_FILE);
    private Properties properties;

    public Config() throws IOException {
        try (InputStream fsin = this.getClass().getClassLoader().getResourceAsStream("template.properties")) {
            if (!configFile.exists()) {
                configFileDir.mkdirs();
                try (FileOutputStream fout = new FileOutputStream(configFile)) {
                    configFile.createNewFile();
                    IOHelper.copy(fsin, fout);
                    fout.flush();
                }
            }
        }
        this.properties = new Properties();
        try (FileInputStream sysIn = new FileInputStream(configFile)) {
            properties.load(sysIn);
        }
    }

    public Properties getProperties() {
        return properties;
    }
}