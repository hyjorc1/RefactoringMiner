package boa_2020;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.refactoringminer.api.GitHistoryRefactoringMiner;
import org.refactoringminer.api.GitService;
import org.refactoringminer.api.Refactoring;
import org.refactoringminer.api.RefactoringHandler;
import org.refactoringminer.rm1.GitHistoryRefactoringMinerImpl;
import org.refactoringminer.util.GitServiceImpl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class test {

	public static void main(String[] args) {
		GitService gitService = new GitServiceImpl();
		GitHistoryRefactoringMiner miner = new GitHistoryRefactoringMinerImpl();
		File gitDir = new File("/Users/hyj/git/BoaData/GitBareRepos/refactoring-toy-example.git");
		try {
			Repository repo = new FileRepositoryBuilder().setGitDir(gitDir).build();
			miner.detectAtCommit(repo, "05c1e773878bbacae64112f70964f4f2f7944398", new RefactoringHandler() {
				@Override
				public void handle(String commitId, List<Refactoring> refactorings) {
					System.out.println("Refactorings at " + commitId);
					for (Refactoring ref : refactorings) {
						System.out.println(ref.toJSON());
					}
//					String s = refactorings.get(0).toJSON();
//					System.out.println(s);
//					JsonParser parser = new JsonParser();
//					JsonElement jsonTree = parser.parse(s);
//					if (jsonTree.isJsonObject()) {
//						System.out.println("yes");
//						JsonObject jsonObject = jsonTree.getAsJsonObject();
//						JsonElement type = jsonObject.get("type");
//						System.out.println(type.getAsString());
//					}
				}
			});

//			miner.detectAll(repo, "master", new RefactoringHandler() {
//				@Override
//				public void handle(String commitId, List<Refactoring> refactorings) {
//					System.out.println("Refactorings at " + commitId);
//					for (Refactoring ref : refactorings) {
//						System.out.println(ref.toString());
//					}
//				}
//			});

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
