package socialmedia;

import java.io.*;
import java.util.ArrayList;

import socialmedia.AccountIDNotRecognisedException;
import socialmedia.IllegalHandleException;
import socialmedia.InvalidHandleException;
import socialmedia.SocialMediaPlatform;
import socialmedia.HandleNotRecognisedException;
import socialmedia.PostIDNotRecognisedException;

/**
 * A class that will implement the methods of the social media platform and mini social media platform interfaces.
 * This provides the majority of the functionallity
 */
public class SocialMedia implements SocialMediaPlatform {
    
    //creating the array lists needed to manage accounts
	ArrayList<Account> accounts = new ArrayList<Account>();
	ArrayList<Integer> ids = new ArrayList<Integer>();
	
	//creating the array lists to manage the posts
	ArrayList<Post> posts = new ArrayList<Post>();
	ArrayList<Integer> postIds = new ArrayList<Integer>();
    
    
    @Override
	public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
		// Method that creates an account object
		
		//validating the inputs from the user
        if (handle.length() > 0){
            boolean unique = true;
            int i=0;
            //looping over the accounts to make sure the handle is unique
            for(Account loopAccount : accounts){
                if(loopAccount.getHandle().equals(handle)){
                    unique = false;
                }
                i++;
            }
            //creating a new account if the handle is unique
            if (unique){
                ids.add(ids.size());
                Account newAccount = new Account(ids.get(ids.size()-1), handle);
                accounts.add(newAccount);
                //returning the account id
                return ids.get(ids.size()-1);
            } else {
                //throwing the exceptions for invalid handles
                throw new IllegalHandleException("handle already in use");
            }
        } else {
            throw new InvalidHandleException("invalid handle length");
        }
	}

	@Override
	public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
		// Method that creates an account object
		
		//validating the inputs from the user
		if (handle.length() > 1){
            boolean unique = true;
            int i=0;
            //looping over the accounts to make sure the handle is unique
            for(Account loopAccount : accounts){
                if(loopAccount.getHandle().equals(handle)){
                    unique = false;
                }
                i++;
            }
            //creating a new account if the handle is unique
            if (unique){
                ids.add(ids.size());
                Account newAccount = new Account(ids.get(ids.size()-1), handle, description);
                accounts.add(newAccount);
                //returning the account id
                return ids.get(ids.size()-1);
            } else {
                //throwing the exceptions for invalid handles
                throw new IllegalHandleException("handle already in use");
            }
        } else {
            throw new InvalidHandleException("invalid handle length");
        }
	}

	@Override
    public void removeAccount(int id) throws AccountIDNotRecognisedException {
        //a function to remove accounts by their id
        boolean exists = false;
        int j = 0;
        String loopHandle = "";
        //looping through the accounts until the id and handle for that account are found and making sure they exists
        while (j < accounts.size()) {
            if (accounts.get(j).getid() == id) {
                exists = true;
                loopHandle = accounts.get(j).getHandle();
            }
            j++;
        }
        //if the acocunt exists, removing the posts from that account
        if (exists){
            //looping through the posts to find the posts from that acount
            for (Post loopPost : posts) {
                if (loopPost.getHandle().equals(loopHandle)) {
                    //using the delete post function for every post, which needs a try catch
                    try{
                        deletePost(loopPost.getId());
                    } catch (PostIDNotRecognisedException e) {
                        System.out.println("PostIDNotRecognisedException thrown");
                    }
                }
            }
        }
        
        int i=0;
        while (i < accounts.size()){
            //removing that account from the array list
            if(accounts.get(i).getid() == id){
                exists = true;
                accounts.remove(i);
            }
            i++;
        }
        //throwing the relevant exceptions
        if (!exists){
            throw new AccountIDNotRecognisedException("account id not recognised");
        }
    }

    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {
        //a function to remove accounts by their handle
        
        //looping through the posts and removing the posts that match the inputed account handle
        for (Post loopPost : posts) {
            if (loopPost.getHandle().equals(handle)) {
                try{
                    deletePost(loopPost.getId());
                } catch (PostIDNotRecognisedException e) {
                    System.out.println("PostIDNotRecognisedException thrown");
                }
            }
        }
        
        boolean exists = false;
        int i=0;
        //looping through the accounts and removing the account with that handle
        while (i < accounts.size()){
            if(accounts.get(i).getHandle().equals(handle)){
                exists = true;
                accounts.remove(i);
            }
            i++;
        }
        //if that handle doesn't exist throw an exception
        if (!exists){
            throw new HandleNotRecognisedException("handle not recognised");
        }
    }

	@Override
	public void changeAccountHandle(String oldHandle, String newHandle) throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
    	//a method that takes an old account handle and replaces it with a new one
    	boolean exists = false;
    	//checking the new handle is correct
    	if (newHandle.length() > 1){
            boolean unique = true;
            //looping through the accounts to make sure that the handle is unique
            for(Account loopAccount : accounts){
                if(loopAccount.getHandle().equals(newHandle)){
                    unique = false;
                }
            }
            if (unique){
                //looping through the current accounts to make sure that the handle exists
                for (Account loopAccount : accounts) {
                    if (loopAccount.getHandle().equals(oldHandle)) {
                        exists = true;
                        loopAccount.setHandle(newHandle);
                    }
                }
            //throwing the relevent exceptions if statements aren't satisfied
                if (!exists){
                    throw new HandleNotRecognisedException("handle not recognised");
                }
            } else {
                //#####throw an exception here######
                throw new IllegalHandleException("handle already in use");
            }
        } else {
            throw new InvalidHandleException("invalid handle length");
        }
	}


	@Override
	public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
        //a method that takes an account handle and description to update the account
		boolean exists = false;
		//looping thwough the accounts to make sure the account exists and then updating the description
		for (Account loopAccount : accounts) {
			if (loopAccount.getHandle().equals(handle)) {
                exists = true;
				loopAccount.setDescription(description);
			}
		}
		//if the account doesn't exists throw an exception
		if (!exists){
            throw new HandleNotRecognisedException("handle not recognised");
		}
	}

	@Override
	public String showAccount(String handle) throws HandleNotRecognisedException {
        //a method to show an account
        boolean exists = false;
    	String printedAccount = "";
    	//looping through the accounts to  make sure the account exists
		for (Account loopAccount : accounts) {
			if (loopAccount.getHandle().equals(handle)) {
                exists = true;
                //then creating a string from the account attributes
				printedAccount += "ID: " + Integer.toString(loopAccount.getid()) + "\n" + "Handle: " + loopAccount.getHandle() + "\n" + "Description: " + loopAccount.getDesc() + "\n" + "Post count: " + loopAccount.getNoPosts() + "\n" + "Endorse count: " + loopAccount.getNoEndorsements() + "\n";
			}
		}
        //if the account exists return the created string and if not throw an exception
		if (exists){
            return printedAccount;
        } else {
            throw new HandleNotRecognisedException("handle not recognised");
        }
	}

	@Override
	public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
        //
        boolean exists = false;
        Account temp = null;
		for (Account loopAccount : accounts) {
			if (loopAccount.getHandle().equals(handle)) {
                exists = true;
                temp = loopAccount;
            }
        }
        if (exists){
            if (message.length() < 100 && message.length() > 0){
                postIds.add(postIds.size());
                Post newPost = new Post(postIds.size()-1, message, handle);
                posts.add(newPost);
                temp.addPost();
                return postIds.get(postIds.size()-1);
            } else {
                throw new InvalidPostException("message length is incorrect");
            }
		} else {
            throw new HandleNotRecognisedException("handle not recognised");
		}
	}

	@Override
	public int endorsePost(String handle, int id) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
		boolean created = false;
		boolean exists = false;
		Account temp = null;
		for (Account loopAccount : accounts) {
			if (loopAccount.getHandle().equals(handle)) {
                exists = true;
            }
        }
        if (exists){
            int i = 0;
            while (i < posts.size()){
                if (posts.get(i).getId() == id) {
                    created = true;
                    postIds.add(postIds.size());
                    String message = "EP@" + posts.get(i).getHandle() + ":" + posts.get(i).getMessage();
                    Post newPost = new Post(postIds.size()-1, message, handle);
                    posts.get(i).addEndorsement();
                    posts.add(newPost);
                    //add endorsment to post handle
                    for (Account loopAccount : accounts) {
                        if (loopAccount.getHandle().equals(posts.get(i).getHandle())) {
                            loopAccount.addEndorsement();
                        }
                    }
                }
                i++;
            }
            if (created) {
                return postIds.get(postIds.size()-1);
            } else {
                throw new PostIDNotRecognisedException("post id does not exist");
            }
        } else {
            throw new HandleNotRecognisedException("handle not recognised");
        }
	}
	
	@Override
	public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,  PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
        boolean created = false;
        boolean exists = false;
        Account temp = null;
        for (Account loopAccount : accounts) {
            if (loopAccount.getHandle().equals(handle)) {
                exists = true;
                temp = loopAccount;
            }
        }
        if (exists){
            //for (Post loopPost : posts) {
            int i = 0;
            while (i < posts.size()){
                if (posts.get(i).getId() == id && !posts.get(i).getMessage().substring(0,3).equals("EP@") && message.length() < 100 && message.length() > 0) {
                    created = true;
                    postIds.add(postIds.size());
                    Post newComment = new Post(postIds.size() - 1, message, handle, "c");
                    posts.add(newComment);
                    posts.get(i).addCommentId(postIds.size() - 1);
                    posts.get(i).addComment();
                    //addPost()
                    temp.addPost();
                }
                i++;
            }
        } else {
            throw new HandleNotRecognisedException("handle not recognised");
        }
        if (created) {
            return postIds.size()-1;
        } else {
            //throw new NotActionablePostException("not actionable post");
            //throw new PostIDNotRecognisedException("post ID not recognised");
            throw new InvalidPostException("invalid post");
        }
    }

	@Override
    public void deletePost(int id) throws PostIDNotRecognisedException {
        boolean exists = false;
        int i=0;
        while (i < posts.size()){
            if(posts.get(i).getId() == id){
                exists = true;
                for (Account loopAccount : accounts){
                    if (loopAccount.getHandle().equals(posts.get(i).getHandle())){
                        loopAccount.removeEndorsements(posts.get(i).getNoEndorsements());
                    }
                }
                posts.get(i).setHandle("");
                posts.get(i).setMessage("The original content was removed from the system and is no longer available.");
                posts.get(i).removeEndorsements();
            }
            i++;
        }
        if (exists) {
            for (Post loopPost : posts) {
                if (loopPost.getId() == id) {
                    String message = "EP@" + loopPost.getHandle() + ":" + loopPost.getMessage();
                    int j = 0;
                    while (j < posts.size()) {
                        if (posts.get(j).getMessage().equals(message)) {
                            posts.remove(j);
                        }
                        j++;
                    }
                }
            }
        } else {
            throw new PostIDNotRecognisedException("post id does not exist");
        }
    }

    @Override
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {
       String individualPost = "";
       boolean exists = false;
       for (Post loopPost : posts) {
           if (loopPost.getId() == id) {
               exists = true;
               individualPost = "ID: " + Integer.toString(loopPost.getId()) + "\n" + "Account: " + loopPost.getHandle() + "\n" + "No. endorsements: " + Integer.toString(loopPost.getNoEndorsements()) + " | No. comments: " + Integer.toString(loopPost.getNoComments()) + "\n" + loopPost.getMessage() + "\n";
           }
       }
       if (exists) {
           return individualPost;
       } else {
           throw new PostIDNotRecognisedException("post id does not exist");
       }
   }

	@Override
    public StringBuilder showPostChildrenDetails(int id) throws PostIDNotRecognisedException, NotActionablePostException {
        boolean exists = false;
        StringBuilder postTree = new StringBuilder();
        for (Post loopPost : posts) {
            if (loopPost.getId() == id) {
                exists = true;
                if (loopPost.getNoComments() > 0){
                    postTree.append(showIndividualPost(id) + "|" + "\n" + "| > ");
                } else {
                    postTree.append(showIndividualPost(id));
                }
            }
        }
        if (exists) {
            int i = 0;
            while (i < posts.size()) {
                if (posts.get(i).getId() == id) {
                    for (int commentId : posts.get(i).getCommentIds()) {
                        StringBuilder temp = showPostChildrenDetails(commentId);
                        int j = 0;
                        while (j < temp.length()-1){
                            //.insert(pos, c);
                            if (temp.substring(j,j+1).equals("\n")){
                                temp.insert(j+1, "    ");
                            }
                            j++;
                        }
                        postTree.append(temp);
                    }
                }
                i++;
            }
            return postTree;
        } else {
            throw new PostIDNotRecognisedException("post id does not exist");
        }
    }   

	@Override
	public int getNumberOfAccounts() {
		//a method to return the number of accounts in the platform
		return accounts.size();
	}

	@Override
	public int getTotalOriginalPosts() {
        //a method to return the number of posts that are origional and not comments or endorsments
        int total = 0;
        //looping through the posts and adding to the total origioal posts if, not a comment and not an endorsement
		for (Post loopPost : posts) {
            if (!loopPost.getMessage().substring(0,3).equals("EP@") && !loopPost.getIsComment() && !loopPost.getHandle().equals("")) {
                total++;
            }
        }
		return total;
	}

	@Override
	public int getTotalEndorsmentPosts() {
        //a method that will return the total number of posts that are endorsements
		int total = 0;
		//looping through the posts and if the posts is an endorsement adding to the total endorsment posts
		for (Post loopPost : posts) {
            if (loopPost.getMessage().substring(0,3).equals("EP@")) {
                total++;
            }
        }
		return total;
	}

	@Override
	public int getTotalCommentPosts() {
        //a method that will return the number of total comments on the platform
		int total = 0;
		//looping through the posts and if it is a comment adding to the total comment posts
		for (Post loopPost : posts) {
            if (loopPost.getIsComment()) {
                total++;
            }
        }
		return total;
	}

	@Override
	public int getMostEndorsedPost() {
        //a method that returns the post that has the most number of endorsements
		int id = 0;
		int max = -1;
		//a max functions that loops through the posts to find the one with the largest number of endorsments
		for (Post loopPost : posts) {
            if (loopPost.getNoEndorsements() > max) {
                max = loopPost.getNoEndorsements();
                id = loopPost.getId();
            }
        }
		return id;
	}

	@Override
	public int getMostEndorsedAccount() {
		//a method that will return the account id with the most endorsments
		int id = 0;
		int max = -1;
		//a max function that loops through the accounts to find the one with the largest number of endorsments
		for (Account loopAccount : accounts) {
            if (loopAccount.getNoEndorsements() > max) {
                max = loopAccount.getNoEndorsements();
                id = loopAccount.getid();
            }
        }
		return id;
	}

	@Override
	public void erasePlatform() {
		//a method to remove all the accounts and posts from the platform
		
        accounts.clear();
        ids.clear();
        posts.clear();
        postIds.clear();
	}

	@Override
	public void savePlatform(String filename) throws IOException {
		//a method to save the platform by writing to a ser file
        try{
        
            FileOutputStream writeData = new FileOutputStream(filename + ".ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(accounts);
            writeStream.writeObject(ids);
            writeStream.writeObject(posts);
            writeStream.writeObject(postIds);
            
            writeStream.flush();
            writeStream.close();
            
        }catch (IOException e) {
            e.printStackTrace();
        }
	}


	@Override
	public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
		//a method to load a platfomr froma ser file that was saved
        try{
            FileInputStream readData = new FileInputStream(filename + ".ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);
            
            accounts = (ArrayList<Account>) readStream.readObject();
            ids = (ArrayList<Integer>) readStream.readObject();
            posts = (ArrayList<Post>) readStream.readObject();
            postIds = (ArrayList<Integer>) readStream.readObject();
            
            readStream.close();
            
        }catch (Exception e) {
            e.printStackTrace();
        }
	}

}
