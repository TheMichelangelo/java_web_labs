package org.tempuri;

import java.rmi.RemoteException;
import java.util.Scanner;

import org.tempuri.CalculatorStub.Add;
import org.tempuri.CalculatorStub.AddResponse;

public class TestService {

	public static void main(String[] args) throws RemoteException {
        Scanner in = new Scanner(System.in);
		CalculatorStub stub = new CalculatorStub("http://www.dneonline.com/calculator.asmx?WSDL");
		System.out.println("Print first integer");
	    int a = in.nextInt();
	    System.out.println("You second integer");
	    int b = in.nextInt();
		Add add = new Add();
		add.setIntA(a);
		add.setIntB(b);
		AddResponse response= stub.add(add);
		int addResult = response.getAddResult();
		System.out.println("Result of adding "+a+" + "+b+" is " + addResult);
	}

}
