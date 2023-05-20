/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.voting;
import sqlMethods.SQLMethods;
import constructors.Voters;
import constructors.Candidates;

/**
 *
 * @author kobe0
 */
public class Voting {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        String[] getPos = SQLMethods.GetPositions();
        for (String po : getPos) {
            System.out.println("Position: " + po);
        }
        String[] getId = SQLMethods.GetIdType();
        for(String id : getId){
            System.out.println("Id Type: " + id);
        }
        SQLMethods sqlMethods = new SQLMethods();
        Voters [] getVoters = sqlMethods.GetVoters();
        for (int i = 0; getVoters[i] != null; i++) {
           System.out.println("Voter Name: " + getVoters[i].name);
        }
        
        Candidates [] getCandidates = sqlMethods.GetCandidates();
        for(int i = 0; getCandidates[i] != null; i++){
            System.out.println("Candidate Name: "+ getCandidates[i].positionName);
        }
        
        //sqlMethods.insertVoter("Harvey Brian Martin", 20, "114 Castro", "Cacarong Matanda", "Bulacan", "Pandi", "Philippines", 3014, 2, "DL-9023asd128u992");
        //sqlMethods.insertCandidate("Angat Buhay", 3, 6);
        //sqlMethods.deleteVoter(3);
        //sqlMethods.deleteCandidate(2);
    }
}
