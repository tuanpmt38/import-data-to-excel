package excel.demo.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamDemo {

    public static void main(String[] args) {

        List<String> listA = new ArrayList<>();
        listA.add("Java");
        listA.add("C++");
        listA.add("PHP");

        List<String> listB = new ArrayList<>();
        listB.add("Java");
        listB.add("Python");
        listB.add("Kotlin");


        //lay cac item ko chung giua 2 list
        List<String> listC = listA.stream().filter(a -> !listB.contains(a)).collect(Collectors.toList());
        System.out.println(" listC :" + listC);

        //lay item chung giua 2 list
        List<String> listD = listA.stream().filter(listB::contains).collect(Collectors.toList());
        System.out.println(" listD :" + listD);


    }

}

