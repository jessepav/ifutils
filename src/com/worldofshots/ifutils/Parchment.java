package com.worldofshots.ifutils;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Parchment
{
    public static void parchmentBase64Encode(Path infile, Path outfile) throws IOException {
        byte[] inbytes = Files.readAllBytes(infile);
        StringBuilder sb = new StringBuilder();
        sb.append("processBase64Zcode('");
        sb.append(Base64.encodeBase64String(inbytes));
        sb.append("')");
        Files.write(outfile, sb.toString().getBytes());
    }
}
