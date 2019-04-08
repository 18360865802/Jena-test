package com.liujie;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class MyQuery {
	public static void main(String[] args) {
		String inputFilename="C:\\KGfile\\kglol.rdf";
		Model model=ModelFactory.createDefaultModel();
		model.read(inputFilename);
		model.write(System.out);
		String queryString="SELECT ?subject ?predicate ?object WHERE{?subject ?predicate ?object}";
		Query query=QueryFactory.create(queryString);
		QueryExecution qExecution=QueryExecutionFactory.create(query, model);
		ResultSet results=qExecution.execSelect();
		ResultSetFormatter.out(System.out,results,query);
//		System.out.println();
		qExecution.close();
		
	}

}
