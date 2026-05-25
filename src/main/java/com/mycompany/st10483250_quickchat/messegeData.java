/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.st10483250_quickchat;

/**
 *
 * @author Andile
 */
class messegeData {
    
String messageID;
int messageNumber;
String recipient;
String message;
String messageHash;
 
public messegeData(String messageID, int messageNumber,String recipient,String message, String messageHash ){
this.messageID=messageID;
this.messageNumber=messageNumber;
this.recipient=recipient;
this.message=message;
this.messageHash=messageHash;
}
}

