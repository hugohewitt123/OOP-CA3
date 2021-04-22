package socialmedia;

import java.io.IOException;
import java.util.ArrayList;

import socialmedia.AccountIDNotRecognisedException;
import socialmedia.IllegalHandleException;
import socialmedia.InvalidHandleException;
import socialmedia.SocialMediaPlatform;
import socialmedia.HandleNotRecognisedException;
import socialmedia.PostIDNotRecognisedException;

/**
 * 
 * 
 */
public class SocialMedia implements SocialMediaPlatform {

	ArrayList<Account> accounts = new ArrayList<Account>();
	ArrayList<Integer> ids = new ArrayList<Integer>();
	
	ArrayList<Post> posts = new ArrayList<Post>();
	ArrayList<Integer> postIds = new ArrayList<Integer>();
    
    @Override
	public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
		// TODO Auto-generated method stub
		//##more handle checks
        if (handle.length() > 1){
            boolean unique = true;
            int i=0;
            for(Account loopAccount : accounts){
                if(loopAccount.getHandle().equals(handle)){
                    unique = false;
                }
                i++;
            }
            if (unique){
                ids.add(ids.size());
                Account newAccount = new Account(ids.get(ids.size()-1), handle);
                accounts.add(newAccount);
                return ids.get(ids.size()-1);
            } else {
                //#######throw an exception here#######
                throw new IllegalHandleException("handle already in use");
            }
        } else {
            throw new InvalidHandleException("invalid handle length");
        }
	}

	@Override
	public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
		// TODO Auto-generated method stub
		if (handle.length() > 1){
            boolean unique = true;
            int i=0;
            for(Account loopAccount : accounts){
                if(loopAccount.getHandle().equals(handle)){
                    unique = false;
                }
                i++;
            }
            if (unique){
                ids.add(ids.size());
                Account newAccount = new Account(ids.get(ids.size()-1), handle, description);
                accounts.add(newAccount);
                return ids.get(ids.size()-1);
            } else {
                //#####throw an exception here######
                throw new IllegalHandleException("handle already in use");
            }
        } else {
            throw new InvalidHandleException("invalid handle length");
        }
	}

	@Override
	public void removeAccount(int id) throws AccountIDNotRecognisedException {
		// TODO Auto-generated method stub
		boolean exists = false;
		int i=0;
        while (i < accounts.size()){
            if(accounts.get(i).getid() == id){
                exists = true;
                accounts.remove(i);
            }
            i++;
        }
        if (!exists){
            throw new AccountIDNotRecognisedException("account id not recognised");
        }
	}

	@Override
	public void removeAccount(String handle) throws HandleNotRecognisedException {
		// TODO Auto-generated method stub
		boolean exists = false;
		int i=0;
		while (i < accounts.size()){
            if(accounts.get(i).getHandle().equals(handle)){
                exists = true;
                accounts.remove(i);
            }
            i++;
		}
		if (!exists){
            throw new HandleNotRecognisedException("handle not recognised");
		}
	}

	@Override
	public void changeAccountHandle(String oldHandle, String newHandle) throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
    	boolean exists = false;
    	if (newHandle.length() > 1){
            boolean unique = true;
            for(Account loopAccount : accounts){
                if(loopAccount.getHandle().equals(newHandle)){
                    unique = false;
                }
            }
            if (unique){
                for (Account loopAccount : accounts) {
                    if (loopAccount.getHandle().equals(oldHandle)) {
                        exists = true;
                        loopAccount.setHandle(newHandle);
                    }
                }
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
		boolean exists = false;
		for (Account loopAccount : accounts) {
			if (loopAccount.getHandle().equals(handle)) {
                exists = true;
				loopAccount.setDescription(description);
			}
		}
		if (!exists){
            throw new HandleNotRecognisedException("handle not recognised");
		}
	}

	@Override
	public String showAccount(String handle) throws HandleNotRecognisedException {
        boolean exists = false;
    	String printedAccount = "";
		for (Account loopAccount : accounts) {
			if (loopAccount.getHandle().equals(handle)) {
                exists = true;
				printedAccount += "ID: " + Integer.toString(loopAccount.getid()) + "\n" + "Handle: " + loopAccount.getHandle() + "\n" + "Description: " + loopAccount.getDesc() + "\n" + "Post count: " + loopAccount.getNoPosts() + "\n" + "Endorse count: " + loopAccount.getNoEndorsements() + "\n";
			}
		}
		if (exists){
            return printedAccount;
        } else {
            throw new HandleNotRecognisedException("handle not recognised");
        }
	}

	@Override
	public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
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
                temp = loopAccount;
            }
        }
        if (exists){
            for (Post loopPost : posts) {
                if (loopPost.getId() == id) {
                    created = true;
                    postIds.add(postIds.size());
                    String message = "EP@" + loopPost.getHandle() + ":" + loopPost.getMessage();
                    Post newPost = new Post(postIds.size()-1, message, handle);
                    loopPost.addEndorsement();
                    posts.add(newPost);
                    temp.addEndorsement();
                }
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
        for (Account loopAccount : accounts) {
            if (loopAccount.getHandle().equals(handle)) {
                exists = true;
            }
        }
        if (exists){
            //for (Post loopPost : posts) {
            int i = 0;
            while (i < posts.size()){
                if (posts.get(i).getId() == id && !posts.get(i).getMessage().substring(0,2).equals("EP@") && message.length() < 100 && message.length() > 0) {
                    created = true;
                    postIds.add(postIds.size());
                    Post newComment = new Post(postIds.size() - 1, message, handle, "c");
                    posts.add(newComment);
                    posts.get(i).addCommentId(postIds.size() - 1);
                    posts.get(i).addComment();
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
               posts.get(i).setHandle("");
               posts.get(i).setMessage("The original content was removed from the system and is no longer available.");
           }
           i++;
       }
       if (exists) {
           for (Post loopPost : posts) {
               if (loopPost.getId() == id) {
                   String message = "EP@" + loopPost.getHandle() + ":" + loopPost.getMessage();
                   int j = 0;
                   while (j < posts.size()) {
                       if (posts.get(i).getMessage().equals(message)) {
                           posts.remove(j);
                       }
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
                postTree.append(showIndividualPost(id) + "|" + "\n" + "| > ");
            }
        }
        if (exists) {
            int i = 0;
            while (i < posts.size()) {
                if (posts.get(i).getId() == id) {
                    for (int commentId : posts.get(i).getCommentIds()) {
                        postTree.append(showPostChildrenDetails(commentId) + "\n" + "|" + "\n" + "| > ");
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
		
		return accounts.size();
	}

	@Override
	public int getTotalOriginalPosts() {
        int total = 0;
		for (Post loopPost : posts) {
            if (!loopPost.getMessage().substring(0,2).equals("EP@") && !loopPost.getIsComment()) {
                total++;
            }
        }
		return total;
	}

	@Override
	public int getTotalEndorsmentPosts() {
		int total = 0;
		for (Post loopPost : posts) {
            if (loopPost.getMessage().substring(0,2).equals("EP@")) {
                total++;
            }
        }
		return total;
	}

	@Override
	public int getTotalCommentPosts() {
		int total = 0;
		for (Post loopPost : posts) {
            if (loopPost.getIsComment()) {
                total++;
            }
        }
		return total;
	}

	@Override
	public int getMostEndorsedPost() {
		int id = 0;
		int max = -1;
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
		//getNoEndorsements()
		int id = 0;
		int max = -1;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void savePlatform(String filename) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

}
