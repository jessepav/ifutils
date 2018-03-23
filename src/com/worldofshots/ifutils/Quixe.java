package com.worldofshots.ifutils;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Quixe
{
    public static void quixeBase64Encode(Path infile, Path outfile) throws IOException {
        byte[] inbytes = Files.readAllBytes(infile);
        StringBuilder sb = new StringBuilder();
        sb.append("$(document).ready(function() {\n" +
                  "  GiLoad.load_run(null, '");
        sb.append(Base64.encodeBase64String(inbytes));
        sb.append("', 'base64');\n" +
                  "});");
        Files.write(outfile, sb.toString().getBytes());
    }

}
