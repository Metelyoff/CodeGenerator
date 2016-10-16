package com.metelyoff.model;
import java.util.*;

public class CodeGenerator
{
	private UserLock userLock;
	private int numberOfCells;
	private int numberOfRange;
	private int maxSizeOfKeyNumbers;
	private ArrayList<String> keysNumber;
	
	public CodeGenerator() {}
	
	public CodeGenerator(UserLock newLock){
		this.userLock=newLock;
		this.numberOfCells=newLock.getLockCells();
		this.numberOfRange=newLock.getLockRange();
		this.keysNumber=new ArrayList<String>(setMaxSizeOfKeyNumbers());
	}

	public void setLock(UserLock userLock){
		this.userLock = userLock;
	}

	public UserLock getLock(){
		return userLock;
	}
	
	private int setMaxSizeOfKeyNumbers() {
		this.maxSizeOfKeyNumbers = 1;
		for (int i = 0; i < numberOfCells; i++) {
			maxSizeOfKeyNumbers *= numberOfRange;
		}
		return maxSizeOfKeyNumbers;
	}
	
	public int getMaxSizeOfKeyNubers() {
		return maxSizeOfKeyNumbers;
	}
	
	private int generateValue() {
		return new Random().nextInt(numberOfRange);
	}
	
	private String generateCode() {
		StringBuilder code = new StringBuilder();
		for (int i = 0; i < numberOfCells; i++) {
			code.append(generateValue());
		}
		return code.toString();
	}
	
	private boolean isContainCode(String checkCode) {
		return keysNumber.contains(checkCode);
	}
	
	public String generateKey(){
		String tempCode = generateCode();
		boolean checkFilling=true;
		while(checkFilling){
			if (keysNumber.size() < maxSizeOfKeyNumbers) {
				if (!isContainCode(tempCode)) {
					checkFilling=false;
				} else {
					checkFilling=true;
					tempCode = generateCode();
				}
			} else {
				checkFilling=false;
			}
		}
		keysNumber.add(tempCode);
		return tempCode;
	}
	
	public ArrayList<String> generateAllListOfKeys(){
		String tempCode = generateCode();
		while (keysNumber.size() < maxSizeOfKeyNumbers) {
			if (!isContainCode(tempCode)) {
				keysNumber.add(tempCode);
			} else {
				tempCode = generateCode();
			}
		}
		return keysNumber;
	}
	
	public void codeIsFound(String lockCode){
		if(!isContainCode(lockCode)){
			keysNumber.add(lockCode);
			userLock.setLockKey(lockCode);
		}else{
			userLock.setLockKey(lockCode);
		}
	}
	
	public boolean deleteCode(String lockCode){
		return keysNumber.remove(lockCode);
	}

	public void clearList(){
		keysNumber.clear();
	}

	public ArrayList<String> getList(){
		return keysNumber;
	}
}
