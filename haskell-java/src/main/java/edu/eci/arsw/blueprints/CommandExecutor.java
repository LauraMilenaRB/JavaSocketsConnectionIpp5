package edu.eci.arsw.blueprints;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.nio.charset.StandardCharsets;


/* 
 * Class containing methods for taking the command string (a string with a digit 1-6)
 * received from the server and converting that into its respective Linux shell command, 
 * then executing it and returning the results.
 */
public class CommandExecutor {


	public  CommandExecutor(){

	}

	/**
	 * Runs the shell command after first using parseCommand() to determine which
	 * command to run.
	 *
	 * @param commandString		A string containing a single digit, 1-6;
	 * @return			A string containing the results of the shell command.
	 */
	public static  String run(String commandString,String file) {
		String result = "";
		String line;
		boolean error=false;
		try {
			// start the shell command running as a child processes
            Process child = Runtime.getRuntime().exec("ghci");
            BufferedWriter w = new BufferedWriter (new OutputStreamWriter (child.getOutputStream()));
            w.write(commandString);
            w.close();
			// open a BufferedReader to read the output of the child process
			BufferedReader output = new BufferedReader(new InputStreamReader(child.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(child.getErrorStream()));
			// while the child process is still outputting, add the output to the result string
            while ((line = errorReader.readLine()) != null) {
                System.out.println(line);
                result = result.concat(line);
                result = result.concat("\n");
                error=true;
            }
            errorReader.close();
			if(!error){
				/*FileWriter escribir=new FileWriter(file,true);
				escribir.write(commandString+"\n");
				escribir.close();
				//Leo el archivo y escribo el comando con todos los comandos
				w.write(commandString);
				w.close();*/
				while ((line = output.readLine()) != null) {
					System.out.println(line);
					result = result.concat(line);
					result = result.concat("\n");
				}

			}




			result = result.concat("\n");
			// add "END_MESSAGE" to the result string. When the client sees END_MESSAGE it
			// will know that the server is done sending
			result = result.concat("END_MESSAGE");
			output.close();
            //child.destroy();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
