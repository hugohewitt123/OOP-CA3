package socialmedia;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * 
 */
public class SocialMedia implements SocialMediaPlatform {

	ArrayList<Account> accounts = new ArrayList<Account>();
	ArrayList<Integer> ids = new ArrayList<Integer>();
    
    @Override
	public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
		// TODO Auto-generated method stub
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
            Account newAccount = new Account(ids.size(), handle);
            accounts.add(newAccount);
            return ids.get(ids.size()-1);
        } else {
            //throw an exception as well
            return -1;
        }
	}

	@Override
	public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
		// TODO Auto-generated method stub
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
            Account newAccount = new Account(ids.size(), handle, description);
            accounts.add(newAccount);
            return ids.get(ids.size()-1);
        } else {
            //throw an exception as well
            return -1;
        }
	}

	@Override
	public void removeAccount(int id) throws AccountIDNotRecognisedException {
		// TODO Auto-generated method stub
		int i=0;
        for (Account loopAccount : accounts){
            if(loopAccount.getid() == id){
                accounts.remove(i);
            }
            i++;
        }
	}

	@Override
	public void removeAccount(String handle) throws HandleNotRecognisedException {
		// TODO Auto-generated method stub
		int i=0;
		while (i < accounts.size()){
            if(accounts.get(i).getHandle().equals(handle)){
                accounts.remove(i);
            }
            i++;
		}
	}

	@Override
	public void changeAccountHandle(String oldHandle, String newHandle)
			throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public String showAccount(String handle) throws HandleNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int endorsePost(String handle, int id)
			throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
			PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deletePost(int id) throws PostIDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public String showIndividualPost(int id) throws PostIDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringBuilder showPostChildrenDetails(int id)
			throws PostIDNotRecognisedException, NotActionablePostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfAccounts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalOriginalPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalEndorsmentPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalCommentPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMostEndorsedPost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMostEndorsedAccount() {
		// TODO Auto-generated method stub
		return 0;
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
