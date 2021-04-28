import socialmedia.AccountIDNotRecognisedException;
import socialmedia.SocialMedia;
import socialmedia.IllegalHandleException;
import socialmedia.InvalidHandleException;
import socialmedia.SocialMediaPlatform;
import socialmedia.HandleNotRecognisedException;
import socialmedia.InvalidPostException;
import socialmedia.PostIDNotRecognisedException;
import socialmedia.NotActionablePostException;

import java.io.*;

/**
 * A short program to illustrate an app testing some minimal functionality of a
 * concrete implementation of the SocialMediaPlatform interface -- note you will
 * want to increase these checks, and run it on your SocialMedia class (not the
 * BadSocialMedia class).
 *
 * 
 * @author Diogo Pacheco
 * @version 1.0
 */
public class SocialMediaPlatformTestApp {

	/**
	 * TEST method. This will test all the functionality of our social media class we created
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		System.out.println("The system compiled and started the execution...");
        
        //creating the new platform
		SocialMediaPlatform platform = new SocialMedia();
        
        //TEST checking the platform is created correctly
		assert (platform.getNumberOfAccounts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalOriginalPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalCommentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalEndorsmentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";

		Integer id=null;
		Integer id1;
		Integer id2;
		
		
		//TEST to check that the creation of the accounts and showing the accounts works
		try {
            System.out.println("------------------showIDs------------------");
			id = platform.createAccount("my_handle");
			System.out.println(id);
			id1 = platform.createAccount("my_handle2", "hello");
			System.out.println(id1);
			id2 = platform.createAccount("my_handle3", "hello");
			System.out.println(id2);
			
			System.out.println("------------------showAccounts------------------");
			System.out.println(platform.showAccount("my_handle"));
			System.out.println(platform.showAccount("my_handle2"));
			System.out.println(platform.showAccount("my_handle3"));

		} catch (IllegalHandleException e) {
			System.out.println("IllegalHandleException thrown");
		} catch (InvalidHandleException e) {
			System.out.println("InvalidHandleException thrown");
		} catch (HandleNotRecognisedException e) {
			System.out.println("HandleNotRecognisedException thrown");
		}
		
		
		//TEST to check that the change account handle works
		try {
            System.out.println("------------------changeAccountHandle------------------");
            platform.changeAccountHandle("my_handle", "Jeff");
            System.out.println(platform.showAccount("Jeff"));
            
		} catch (IllegalHandleException e) {
			System.out.println("IllegalHandleException thrown");
		} catch (InvalidHandleException e) {
			System.out.println("InvalidHandleException thrown");
		} catch (HandleNotRecognisedException e) {
			System.out.println("HandleNotRecognisedException thrown");
		}
		
		
		//TEST to check that the update account description works
		try {
            System.out.println("-----------------updateAccountDescription-------------------");
            platform.updateAccountDescription("my_handle2", "my name is");
            System.out.println(platform.showAccount("my_handle2"));
            
		} catch (HandleNotRecognisedException e) {
			System.out.println("HandleNotRecognisedException thrown");
		}
		
		
		//TEST to check the post works
		try {
            System.out.println("-----------------createPosts-------------------");
            System.out.println(platform.createPost("my_handle2", "this is my first post"));
            System.out.println(platform.createPost("Jeff", "this is MY first post"));
            System.out.println("-----------------showIndividualPosts-------------------");
            System.out.println(platform.showIndividualPost(0));
            System.out.println(platform.showIndividualPost(1));
            
		} catch (HandleNotRecognisedException e) {
			System.out.println("HandleNotRecognisedException thrown");
		} catch (InvalidPostException e) {
            System.out.println("InvalidPostException thrown");
		} catch (PostIDNotRecognisedException e) {
            System.out.println("PostIDNotRecognisedException thrown");
		}
		
		//TEST to check the endorsments and post details works
		
		
		
		//TEST to check the comments and post details works
		try {
            System.out.println("-----------------commentPost-------------------");
            //comments on posts
            System.out.println(platform.commentPost("Jeff", 0, "a comment"));
            System.out.println(platform.commentPost("my_handle2", 1, "another comment"));
            System.out.println(platform.commentPost("my_handle3", 0, "a aklfrav comment"));
            
            //comments on comments
            System.out.println(platform.commentPost("Jeff", 2, "another imb comment"));
            System.out.println(platform.commentPost("my_handle3", 4, "another kjasflj comment"));
            
            System.out.println("-----------------showPostChildrenDetails-------------------");
            System.out.println(platform.showPostChildrenDetails(0));
            System.out.println(platform.showPostChildrenDetails(1));
            
		} catch (HandleNotRecognisedException e) {
			System.out.println("HandleNotRecognisedException thrown");
		} catch (InvalidPostException e) {
            System.out.println("InvalidPostException thrown");
		} catch (PostIDNotRecognisedException e) {
            System.out.println("PostIDNotRecognisedException thrown");
		} catch (NotActionablePostException e) {
            System.out.println("NotActionablePostException thrown");
		}
		
		
		//TEST 
		try {
            System.out.println("-----------------endorsePost-------------------");
            //comments on posts
            System.out.println(platform.endorsePost("my_handle3", 0));
            System.out.println(platform.endorsePost("Jeff", 1));
            
            System.out.println("-----------------showPostChildrenDetails-------------------");
            System.out.println(platform.showPostChildrenDetails(0));
            System.out.println(platform.showPostChildrenDetails(1));
        } catch (HandleNotRecognisedException e) {
			System.out.println("HandleNotRecognisedException thrown");
		} catch (PostIDNotRecognisedException e) {
            System.out.println("PostIDNotRecognisedException thrown");
		} catch (NotActionablePostException e) {
            System.out.println("NotActionablePostException thrown");
		}
		
		
		//TEST to check that the creation of the accounts and showing the accounts works
		try {
			System.out.println("------------------showAccounts------------------");
			System.out.println(platform.showAccount("Jeff"));
			System.out.println(platform.showAccount("my_handle2"));
			System.out.println(platform.showAccount("my_handle3"));

		} catch (HandleNotRecognisedException e) {
			System.out.println("HandleNotRecognisedException thrown incorrectly");
		}
		
		
		//TEST to check that the erase platform works
		try{
            platform.savePlatform("paul");
			
		} catch (IOException e) {
			System.out.println(e);
		}
        
        
		//TEST to check that the remove account by id works and the remove by handle works
		try{
            System.out.println("-----------------removeAccounts-------------------");
            platform.removeAccount(id);
			assert (platform.getNumberOfAccounts() == 2) : "number of accounts registered in the system does not match";
			
            platform.removeAccount("my_handle2");
			assert (platform.getNumberOfAccounts() == 1) : "number of accounts registered in the system does not match";
			
		} catch (HandleNotRecognisedException e) {
			System.out.println("HandleNotRecognisedException thrown incorrectly");
		} catch (AccountIDNotRecognisedException e) {
			System.out.println("AccountIDNotRecognizedException thrown incorrectly");
		}
		
		
		//TEST to check that the creation of the accounts and showing the accounts works
		try {
			System.out.println("------------------showAccounts------------------");
			System.out.println(platform.showAccount("my_handle3"));
			
			System.out.println("-----------------showPostChildrenDetails-------------------");
            System.out.println(platform.showPostChildrenDetails(0));
            System.out.println(platform.showPostChildrenDetails(1));

		} catch (HandleNotRecognisedException e) {
			System.out.println("HandleNotRecognisedException thrown");
		} catch (PostIDNotRecognisedException e) {
            System.out.println("PostIDNotRecognisedException thrown");
		} catch (NotActionablePostException e) {
            System.out.println("NotActionablePostException thrown");
		}
		
		
		//TEST to check that the methods that return information about the platform work
		try{
            System.out.println("------------------getTotalOriginalPosts------------------");
            System.out.println(platform.getTotalOriginalPosts());
            
            System.out.println("------------------getTotalEndorsmentPosts------------------");
            System.out.println(platform.getTotalEndorsmentPosts());
            
            System.out.println("------------------getTotalCommentPosts------------------");
            System.out.println(platform.getTotalCommentPosts());
            
            System.out.println("------------------getMostEndorsedPost------------------");
            System.out.println("Post ID: " + platform.getMostEndorsedPost());
            
            System.out.println("------------------getMostEndorsedAccount------------------");
            System.out.println("Account ID: " + platform.getMostEndorsedAccount());
            
            System.out.println();
			//assert (platform.getNumberOfAccounts() == 1) : "number of accounts registered in the system does not match";
			
		} catch (Exception e) {
			System.out.println(e);
		}
        
        
        //TEST to check that the erase platform works
		try{
            platform.savePlatform("james");
			
		} catch (IOException e) {
			System.out.println(e);
		}
        
        
        //TEST to check that the erase platform works
		try{
            platform.erasePlatform();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//TEST to check that the erase platform works
		try{
            platform.loadPlatform("paul");
			
		} catch (IOException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}

}
