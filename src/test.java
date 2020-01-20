

import java.util.List;

import org.eclipse.jgit.lib.Repository;
import org.refactoringminer.api.GitHistoryRefactoringMiner;
import org.refactoringminer.api.GitService;
import org.refactoringminer.api.Refactoring;
import org.refactoringminer.api.RefactoringHandler;
import org.refactoringminer.rm1.GitHistoryRefactoringMinerImpl;
import org.refactoringminer.util.GitServiceImpl;

public class test {

	public static void main(String[] args) throws Exception {
		GitService gitService = new GitServiceImpl();
		GitHistoryRefactoringMiner miner = new GitHistoryRefactoringMinerImpl();

		Repository repo = gitService.cloneIfNotExists("tmp/okhttp",
				"https://github.com/square/okhttp.git");

		miner.detectAtCommit(repo, "c753d2e41ba667f9b5a31451a16ecbaecdc65d80", new RefactoringHandler() {
			@Override
			public void handle(String commitId, List<Refactoring> refactorings) {
				System.out.println("Refactorings at " + commitId);
				for (Refactoring ref : refactorings) {
					System.out.println(ref.toString());
				}
			}
		});
		
//		miner.detectAll(repo, "master", new RefactoringHandler() {
//			@Override
//			public void handle(String commitId, List<Refactoring> refactorings) {
//				System.out.println("Refactorings at " + commitId);
//				for (Refactoring ref : refactorings) {
//					System.out.println(ref.toString());
//				}
//			}
//		});
	}

}
