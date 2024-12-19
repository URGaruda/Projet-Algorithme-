
all: Test_Patricia.class fatiMain.class

Test_Patricia.class: Test_Patricia.java Patricia_Trie.java
	javac Test_Patricia.java Patricia_Trie.java

fatiMain.class: fatiMain.java 
	javac fatiMain.java 

clean:
	rm -f *.class