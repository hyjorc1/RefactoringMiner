package boa_2020;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.refactoringminer.api.GitHistoryRefactoringMiner;
import org.refactoringminer.api.GitService;
import org.refactoringminer.api.Refactoring;
import org.refactoringminer.api.RefactoringHandler;
import org.refactoringminer.rm1.GitHistoryRefactoringMinerImpl;
import org.refactoringminer.util.GitServiceImpl;

public class test {
	
	private static ExecutorService executor;

	public static void main(String[] args) {
		GitService gitService = new GitServiceImpl();
		GitHistoryRefactoringMiner miner = new GitHistoryRefactoringMinerImpl();
		File gitDir = new File("/Users/hyj/git/BoaData/GitBareRepos/refactoring-toy-example.git");
		try {
			Repository repo = new FileRepositoryBuilder().setGitDir(gitDir).build();
			miner.detectAtCommit(repo, "0e193b7d02902c6f2abf7c88eebe937d1ac5fc51", new RefactoringHandler() {
				@Override
				public void handle(String commitId, List<Refactoring> refactorings) {
					System.out.println("Refactorings at " + commitId);
					for (Refactoring ref : refactorings) {
						System.out.println(ref.toJSON());
					}
				}
			});
			

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
