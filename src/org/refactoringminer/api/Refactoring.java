package org.refactoringminer.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gr.uom.java.xmi.diff.CodeRange;

public interface Refactoring extends Serializable, CodeRangeProvider {

	public RefactoringType getRefactoringType();
	
	public String getName();

	public String toString();
	
	public List<String> getInvolvedClassesBeforeRefactoring();
	
	public List<String> getInvolvedClassesAfterRefactoring();
	
	default public String toJSON() {
		StringBuilder sb = new StringBuilder();
		sb.append("{").append("\n");
		sb.append("\t").append("\"").append("type").append("\"").append(": ").append("\"").append(getName()).append("\"").append(",").append("\n");
		sb.append("\t").append("\"").append("description").append("\"").append(": ").append("\"").append(toString().replace('\t', ' ')).append("\"").append(",").append("\n");
		sb.append("\t").append("\"").append("leftSideLocations").append("\"").append(": ").append(getFirst(leftSide())).append(",").append("\n");
		sb.append("\t").append("\"").append("rightSideLocations").append("\"").append(": ").append(getFirst(rightSide())).append("\n");
		sb.append("}");
		return sb.toString();
	}
	
	default List<CodeRange> getFirst(List<CodeRange> list) {
		List<CodeRange> l = new ArrayList<CodeRange>();
		l.add(list.get(0));
		return l;
	}
}