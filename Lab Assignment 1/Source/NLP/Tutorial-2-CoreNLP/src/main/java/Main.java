import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.*;
import static java.nio.file.StandardCopyOption.*;



public class Main {
    public static void main(String args[]) throws IOException {
        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        //Reading a file
        File captions = new File("C:\\Users\\saisa\\Desktop\\BIG DATA ANALYTICS AND APPLICATIONS\\Flickr8k\\Flickr8k.token.txt\\");
        BufferedReader bf = new BufferedReader(new FileReader(captions));
        String caption;

        //intialiaxiotn
        List<String> keywordList = new ArrayList<>();
        List<String> cupImageList = new ArrayList<>();
        List<String> guitarImageList = new ArrayList<>();
        List<String> lampImageList = new ArrayList<>();
        keywordList.add("cup");
        keywordList.add("guitar");
        keywordList.add("lamp");


        //intializing map with 0's
        Map<String, Integer> keywordmap = new HashMap<String, Integer>();
        for (String item : keywordList) {
            keywordmap.put(item, 0);
        }
        while((caption = bf.readLine())!=null) {
            System.out.println(caption);


            // read some text in the text variable
            // Add your text here!

            // create an empty Annotation just with the given text
            Annotation document = new Annotation(caption);

            // run all Annotators on this text
            pipeline.annotate(document);

            // these are all the sentences in this document
            // a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
            List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

            for (CoreMap sentence : sentences) {
                // traversing the words in the current sentence
                // a CoreLabel is a CoreMap with additional token-specific methods
                for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {

                    // this is the text of the token
                    String word = token.get(CoreAnnotations.TextAnnotation.class);
                    System.out.println("Text Annotation");
                    System.out.println(token + ":" + word);

                    // this is the POS tag of the token
                    String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);
                    System.out.println("Lemma Annotation");
                    System.out.println(token + ":" + lemma);

                    if (keywordmap.containsKey(lemma)) {

                        if(lemma.equals("cup")){

                            Pattern pattern = Pattern.compile("[0-9]*.jpg");
                            Matcher matcher = pattern.matcher(caption);
                            if (matcher.find()){
                                cupImageList.add(matcher.group(0));
                                Path temp = Files.copy(Paths.get("C:\\Users\\saisa\\Desktop\\BIG DATA ANALYTICS AND APPLICATIONS\\Flickr8k\\Flicker8k_Dataset\\" + matcher.group(0)), Paths.get("C:\\Users\\saisa\\Desktop\\BIG DATA ANALYTICS AND APPLICATIONS\\Flickr8k\\Data\\cup\\" + matcher.group(0)), REPLACE_EXISTING);
                            }
                        }
                        if(lemma.equals("guitar")){

                            Pattern pattern = Pattern.compile("[0-9]*.jpg");
                            Matcher matcher = pattern.matcher(caption);
                            if (matcher.find()){
                                guitarImageList.add(matcher.group(0));
                                Path temp = Files.copy(Paths.get("C:\\Users\\saisa\\Desktop\\BIG DATA ANALYTICS AND APPLICATIONS\\Flickr8k\\Flicker8k_Dataset\\" + matcher.group(0)), Paths.get("C:\\Users\\saisa\\Desktop\\BIG DATA ANALYTICS AND APPLICATIONS\\Flickr8k\\Data\\guitar\\" + matcher.group(0)), REPLACE_EXISTING);


                            }
                        }
                        if(lemma.equals("lamp")){

                            Pattern pattern = Pattern.compile("[0-9]*.jpg");
                            Matcher matcher = pattern.matcher(caption);
                            if (matcher.find()){
                                lampImageList.add(matcher.group(0));
                                Path temp = Files.copy(Paths.get("C:\\Users\\saisa\\Desktop\\BIG DATA ANALYTICS AND APPLICATIONS\\Flickr8k\\Flicker8k_Dataset\\" + matcher.group(0)), Paths.get("C:\\Users\\saisa\\Desktop\\BIG DATA ANALYTICS AND APPLICATIONS\\Flickr8k\\Data\\lamp\\" + matcher.group(0)), REPLACE_EXISTING);

                            }
                        }
                        keywordmap.put(lemma, keywordmap.get(lemma) + 1);

                    }

                    System.out.println("_______________________________________________");

                }
            }
        }
        System.out.println("Image Statistics: ");
        System.out.println(keywordmap);
        System.out.println(cupImageList);
        System.out.println(guitarImageList);
        System.out.println(lampImageList);
    }
}
