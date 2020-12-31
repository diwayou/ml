package com.diwayou.ml;

import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
        String logFile = "/Users/gaopeng/Downloads/java/ml/pom.xml";
        SparkSession spark = SparkSession.builder().appName("Simple Application").master("local[*]").getOrCreate();
        Dataset<String> logData = spark.read().textFile(logFile).cache();

        long numAs = logData.filter((FilterFunction<String>) s -> s.contains("a")).count();
        long numBs = logData.filter((FilterFunction<String>) s -> s.contains("b")).count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);

        TimeUnit.MINUTES.sleep(2);

        spark.stop();
    }
}
