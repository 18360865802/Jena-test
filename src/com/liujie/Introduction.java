package com.liujie;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.VCARD;

public class Introduction {

	public static void main(String[] args) {
		String[] personURI ={ "http://somewhere/Person1", "http://somewhere/Person2",
				"http://somewhere/Person3","http://somewhere/Person4"};		
		String[] fullName = {"张三","李四","王五","赵六"};	
		Model model =  ModelFactory.createDefaultModel();
		for (int i=0;i<personURI.length;i++){			
			Resource person = model.createResource(personURI[i]);
			person.addProperty(VCARD.FN, fullName[i]);
		}
		FileWriter fwriter = null;
		try {
			fwriter = new FileWriter("D:\\person.rdf");//没有文件会自动创建
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.write(fwriter);	//写入文件,默认是xml方式,可以自己指定
		model.write(System.out,"Turtle");//以Turtle的形式标准输出至控制台
		StmtIterator iter = model.listStatements();
		while(iter.hasNext())
		{
			Statement statement = iter.nextStatement();
			Resource subject = statement.getSubject();
			Property predicate = statement.getPredicate();
			RDFNode object = statement.getObject();
			
			System.out.print(subject.toString()+"  "+predicate.toString()+"  ");
			if(object instanceof Resource){
				System.out.println(object.toString()+".");
			}else{
				System.out.println("\""+object.toString()+"\""+".");
			}			
		}

	}

}
