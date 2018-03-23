package com.worldofshots.ifutils;

import java.io.*;
import java.nio.file.*;

import com.sanityinc.jargs.CmdLineParser;
import com.sanityinc.jargs.CmdLineParser.Option;

public class IFUtils
{
    private static String usageText;

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            println(getUsageText());
            System.exit(1);
        }

        CmdLineParser parser = new CmdLineParser();
        Option<Boolean> parchment64Opt = parser.addBooleanOption('p', "parchment64");
        Option<Boolean> quixe64Opt = parser.addBooleanOption('q', "quixe64");
        Option<Boolean> helpOpt = parser.addBooleanOption('h', "help");

        try {
            parser.parse(args);
        } catch (CmdLineParser.OptionException e) {
            println(e.getMessage() + "\n");
            println(getUsageText());
            System.exit(2);
        }
        String[] files = parser.getRemainingArgs();

        // Commands
        if (parser.getOptionValue(helpOpt, Boolean.FALSE)) {
            println(getUsageText());
            System.exit(0);
        } else if (parser.getOptionValue(parchment64Opt, Boolean.FALSE)) {
            if (files.length != 2) {
                println(getUsageText());
                System.exit(2);
            }
            Parchment.parchmentBase64Encode(Paths.get(files[0]), Paths.get(files[1]));
        } else if (parser.getOptionValue(quixe64Opt, Boolean.FALSE)) {
            if (files.length != 2) {
                println(getUsageText());
                System.exit(2);
            }
            Quixe.quixeBase64Encode(Paths.get(files[0]), Paths.get(files[1]));
        }
    }

    private static String getUsageText() throws UnsupportedEncodingException {
        if (usageText == null) {
            InputStream in = IFUtils.class.getResourceAsStream("usage.txt");
            Reader reader = new InputStreamReader(in, "UTF-8");
            usageText = slurpReaderText(reader, 256);
        }
        return usageText;
    }

    static void println(String msg) {
        System.out.println(msg);
    }

    static String slurpReaderText(Reader r, int bufferSize) {
        String s = "";
        try (BufferedReader br = new BufferedReader(r)) {
            char [] buffer = new char[bufferSize];
            StringBuilder sb = new StringBuilder(5*bufferSize);
            int n;
            while ((n = br.read(buffer, 0, buffer.length)) != -1)
                sb.append(buffer, 0, n);
            s = sb.toString();
        } catch (IOException ex) {
            // It's fine to do nothing and let the default empty string value of
            // 's' be returned.
        }
        return s;
    }
}
