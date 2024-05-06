package com.phonepe.pheonepeMC;

import com.phonepe.pheonepeMC.dtos.DocumentRequest;
import com.phonepe.pheonepeMC.dtos.UserRequest;
import com.phonepe.pheonepeMC.repository.DocumentHistoryImp;
import com.phonepe.pheonepeMC.repository.DocumentRepoImpl;
import com.phonepe.pheonepeMC.repository.DocumentUserRepoImp;
import com.phonepe.pheonepeMC.repository.UserRepoImp;
import com.phonepe.pheonepeMC.services.DocumentService;
import com.phonepe.pheonepeMC.services.UserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
public class PheonepeMcApplication {

	public static void main(String[] args) {
		UserService userService = new UserService(new UserRepoImp());
		DocumentService documentService = new DocumentService(new DocumentRepoImpl(),new DocumentHistoryImp(),new DocumentUserRepoImp());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
			boolean flag = true;
			while(flag){
				Integer choice= Integer.parseInt(br.readLine());
				System.out.println("Select One Of The following");
				System.out.println("1. Create User");
				System.out.println("2. Get User");
				System.out.println("3. Delete User");
				System.out.println("4. Create Document");
				System.out.println("5. Update Document");
				System.out.println("6. revert To Previous Version");
				System.out.println("7. Exit");
				switch (choice){
					case 1:{
						System.out.println("Enter user mail");
						String mail =br.readLine().trim();
						System.out.println("Enter user password");
						String password =br.readLine().trim();
						UserRequest userRequest = new UserRequest(mail);
						userRequest.setPassword(password);
						userService.createUser(userRequest);
						break;
					}
					case 2:{
						System.out.println("Enter user mail");
						String id =br.readLine();
						userService.getUser(id);
						break;
					}
					case 3:{
						System.out.println("Enter user mail");
						String id =br.readLine();
						userService.deleteUser(id);
						break;

					}
					case 4:{
						System.out.println("Enter doc name");
						String name = br.readLine().trim();
						System.out.println("Enter data");
						String data = br.readLine().trim();
						System.out.println("Enter User");
						String user = br.readLine().trim();
						DocumentRequest documentRequest =new DocumentRequest(name,data);
						documentRequest.setCreatedBy(user);
						documentRequest.setUpdatedBy(user);
						System.out.println("Enter login key");
						String loginKey = br.readLine().trim();
						documentService.createDocument(documentRequest,loginKey);
						break;

					}
					case 5:{
						System.out.println("Enter doc name");
						String name = br.readLine().trim();
						System.out.println("Enter data");
						String data = br.readLine().trim();
						System.out.println("Enter User");
						String user = br.readLine().trim();
						DocumentRequest documentRequest =new DocumentRequest(name,data);
						documentRequest.setCreatedBy(user);
						documentRequest.setUpdatedBy(user);
						System.out.println("Enter login key");
						String loginKey = br.readLine().trim();
						documentService.updateDocument(documentRequest,loginKey);
						break;

					}
					case 6:{
						System.out.println("Enter doc id");
						String docId = br.readLine().trim();
						System.out.println("Enter Version");
						Integer version = Integer.parseInt(br.readLine().trim());
						System.out.println("Enter mail");
						String mail  = (br.readLine().trim());
						System.out.println("Enter login key");
						String loginKey = br.readLine().trim();
						documentService.revertDocument(docId,version,mail,loginKey);

						break;

					}
				}

			}

		}
		catch (Exception e){
			System.out.println(String.format("Something wrong:%s",e.getMessage()));
		}
	}

}
