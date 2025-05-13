package edu.grinnell.csc207.spellchecker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * A spellchecker maintains an efficient representation of a dictionary for
 * the purposes of checking spelling and provided suggested corrections.
 * By Emily and Finn. Unfinished, but figured we should submit what we have
 */
public class SpellChecker {
    /** The number of letters in the alphabet. */
    private static final int NUM_LETTERS = 26;

    /** The path to the dictionary file. */
    private static final String DICT_PATH = "words_alpha.txt";

    /**
     * @param filename the path to the dictionary file
     * @return a SpellChecker over the words found in the given file.
     */
    public static SpellChecker fromFile(String filename) throws IOException {
        return new SpellChecker(Files.readAllLines(Paths.get(filename)));
    }

    // Part 1: We drew the dog tree on a whiteboard.

    /** A Node of the SpellChecker structure. */
    private class Node {
        public char ch;
        public ArrayList<Node> children;

        public Node(char ch, ArrayList<Node> children) {
            this.ch = ch;
            this.children = children;
        }
    }

    /** The root of the SpellChecker */
    private Node root;

    public SpellChecker(List<String> dict) throws IllegalArgumentException{
        if (dict == null) {
            throw new IllegalArgumentException();
        }
        String first = dict.get(0);
        char[] firstChars = new char[first.length()];
        first.getChars(0, first.length(), firstChars, 0);
        ArrayList<Node> firstElts = new ArrayList<Node>();
        root = new Node(firstChars[0], firstElts);
        Node cursor = root; 
        for (int i = 1; i < first.length(); i++) {
            Node newNode = new Node(firstChars[i], new ArrayList<Node>());
            cursor.children.set(0, newNode);
        }
        for (int i = 1; i < dict.size(); i++) {
            String s = dict.get(i);
            char[] curChars = new char[s.length()];
            s.getChars(0, s.length(), curChars, 0);
            ArrayList<Node> nodeElts = new ArrayList<Node>();
            
            Node cur = root;
            for (int j = 1; j < s.length(); i++) {
                // Idea: while at a node, search nodes around for the relevant character.
            }

        }
    }

    public void add(String word) {
        // TODO: implement me!
    }

    public boolean isWord(String word) {
        // TODO: implement me!
        return false;
    }

    public List<String> getOneCharCompletions(String word) {
        // TOOD: implement me!
        return null;
    }

    public List<String> getOneCharEndCorrections(String word) {
        // TODO: implement me!
        return null;
    }

    public List<String> getOneCharCorrections(String word) {
        // TODO: implement me!
        return null;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java SpellChecker <command> <word>");
            System.exit(1);
        } else {
            String command = args[0];
            String word = args[1];
            SpellChecker checker = SpellChecker.fromFile(DICT_PATH);
            switch (command) {
                case "check": {
                    System.out.println(checker.isWord(word) ? "correct" : "incorrect");
                    System.exit(0);
                }

                case "complete": {
                    List<String> completions = checker.getOneCharCompletions(word);
                    for (String completion : completions) {
                        System.out.println(completion);
                    }
                    System.exit(0);
                }

                case "correct": {
                    List<String> corrections = checker.getOneCharEndCorrections(word);
                    for (String correction : corrections) {
                        System.out.println(correction);
                    }
                    System.exit(0);
                }

                default: {
                    System.err.println("Unknown command: " + command);
                    System.exit(1);
                }
            }
        }
    }
}

