package com.company;

import com.company.GrammerAnalyse.GrammerAnalyse;
import com.company.TokenAnalyse.Token;
import com.company.TokenAnalyse.TokenAnalyse;
import com.company.Util.Pair;

import java.io.IOException;
import java.util.Optional;

public class Main {
    public static void main(String[] args){
        GrammerAnalyse grammerAnalyser = new GrammerAnalyse();
        try {
            grammerAnalyser.DoGrammerAnalyse(args[0], args[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
