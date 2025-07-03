import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.nio.file.*; // For Path, Files, and other file-related utilities
import java.nio.file.attribute.BasicFileAttributes; // For file attribute-related operations
import java.nio.file.FileVisitResult; // For controlling file tree traversal
import java.nio.file.SimpleFileVisitor; // For defining custom behavior during file tree traversal

public class firefoxThemes{
  public static Scanner scnr = new Scanner(System.in);
  
  //public static String themes_directory = "\\home\\keswel\\Documents\\firefox-themes";
  public static String current_theme = "";
  public static String themes_directory = "";
  public static String firefox_chrome_directory = "";

  public static ArrayList<Path> themes = new ArrayList<>();
  public static ArrayList<Path> theme_names = new ArrayList<>();
  
  // config data
  public static ArrayList<String> saved_data = new ArrayList<>();
  public static ArrayList<String> parsed_saved_data = new ArrayList<>();
  
  // PROBLEM: themes_directory not getting updated before this is ran.
  public static void read_themes_folder(String themes_directory) {
    // this part is fucking up somehow
    Path dirPath = Paths.get(themes_directory);

    try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath)) {
      for (Path entry : stream) {
        //System.out.println(entry.toAbsolutePath());

        // add directory of theme folder to themes array.
        themes.add(entry.toAbsolutePath());
        theme_names.add(entry.getFileName());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static void initialize_global_variables() {
    current_theme = "";
    themes_directory = "";
    firefox_chrome_directory = "";
    themes.clear();
    theme_names.clear();
    saved_data.clear();
    parsed_saved_data.clear();
  }
  public static void print_header() {
    System.out.print("███████╗██╗██████╗ ███████╗███████╗ ██████╗ ██╗  ██╗         "+"\n"+"██╔════╝██║██╔══██╗██╔════╝██╔════╝██╔═══██╗╚██╗██╔╝         "+"\n"+"█████╗  ██║██████╔╝█████╗  █████╗  ██║   ██║ ╚███╔╝          "+"\n"+"██╔══╝  ██║██╔══██╗██╔══╝  ██╔══╝  ██║   ██║ ██╔██╗          "+"\n"+"██║     ██║██║  ██║███████╗██║     ╚██████╔╝██╔╝ ██╗         "+"\n"+"╚═╝     ╚═╝╚═╝  ╚═╝╚══════╝╚═╝      ╚═════╝ ╚═╝  ╚═╝         "+"\n"+"                                                             "+"\n"+"████████╗██╗  ██╗███████╗███╗   ███╗███████╗███████╗ ██████╗ "+"\n"+"╚══██╔══╝██║  ██║██╔════╝████╗ ████║██╔════╝██╔════╝██╔═████╗"+"\n"+"   ██║   ███████║█████╗  ██╔████╔██║█████╗  ███████╗██║██╔██║"+"\n"+"   ██║   ██╔══██║██╔══╝  ██║╚██╔╝██║██╔══╝  ╚════██║████╔╝██║"+"\n"+"   ██║   ██║  ██║███████╗██║ ╚═╝ ██║███████╗███████║╚██████╔╝"+"\n"+"   ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝     ╚═╝╚══════╝╚══════╝ ╚═════╝ "+"\n\n");
  }

  public static void print_current_available_themes() {
    System.out.println("themes available: ");

    for (int i=0; i<themes.size(); i++) {
    //System.out.println("\t"+themes.get(i));
      System.out.println("\t"+i+". "+theme_names.get(i)+"\n\t\t[dir: "+themes.get(i)+"]\n");
      //System.out.println("\t\t"+i+". "+themes.get(i));
    }


    System.out.println();
  }

  public static void deleteDirectory(String directoryFilePath) throws IOException
  {
    Path directory = Paths.get(directoryFilePath);

    if (Files.exists(directory))
    {
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>()
        {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException
            {
                Files.delete(path);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path directory, IOException ioException) throws IOException
            {
                Files.delete(directory);
                return FileVisitResult.CONTINUE;
            }
        });
    }
  }

  public static boolean delete_chrome_files() {
    // this should delete everything in the firefox folder.
    System.out.println("are you sure you want to delete old theme files?\n(they will still be available in your themes folder)\n[y/n]");
    String user_choice = scnr.nextLine();
    // problem is here. doesn't asses whether its y or not correctly.
    if (user_choice.toUpperCase().equals("Y")) {
      try {
        System.out.println("trying to delete directory!\n");
        deleteDirectory(firefox_chrome_directory);
        return true;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return false;
  } 

  // copy theme files over to chrome folder.
  public static void copyDirectory(String sourceDirectory, String destinationDirectory) throws IOException {
    Files.walk(Paths.get(sourceDirectory))
      .forEach(source -> {
        Path destination = Paths.get(destinationDirectory, source.toString()
          .substring(sourceDirectory.length()));
        try {
          Files.copy(source, destination);
        } catch (IOException e) {
         // e.printStackTracke();
         System.out.println("error copying "+ sourceDirectory + " to "+ destinationDirectory);
        }
   });
  }

  public static void move_theme_to_chrome(int themeSelected) {
    System.out.println("\napplying selected theme to chrome!\n");
    try {
      System.out.println("Are you sure you want to move: \n"+themes.get(themeSelected).toString()+"\nto\n"+firefox_chrome_directory+"?");
      String user_choice = scnr.nextLine();

      if (user_choice.toUpperCase().equals("Y")) {
        copyDirectory(themes.get(themeSelected).toString(), firefox_chrome_directory);
      }else{
        System.out.println("error: could not copy files! (user selected "+user_choice.toUpperCase()+")");
        return;
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  // TODO: make this a little more streamlined.
  public static void change_theme() {
    // 1. warn user that current theme will be deleted (but stored in themes folder)
    int selected_theme = 0; 
    // 2. ask user to select theme.
    while (true) {
      System.out.println("\nplease select one of the themes available! :)\n (enter the number that corresponds to the theme you'd like to select)\n");
      selected_theme = scnr.nextInt();
      String user_choice = scnr.nextLine();

      System.out.println("Are you sure that you want to select theme "+theme_names.get(selected_theme)+"? [y/n]");
      user_choice = scnr.nextLine();
      if (user_choice.toUpperCase().equals("Y")) {
        break;
      }
    }
    
    // offers to delete old files from directory
    boolean were_files_deleted = delete_chrome_files();
    // checks if user chose to delete files from chrome directory.
    if (were_files_deleted) {
      move_theme_to_chrome(selected_theme);
      // update config file for new theme file.
      update_config_file("current-theme", String.valueOf(selected_theme));
      //parsed_saved_data.set(1, themes_name.get(selected_theme));
      // 4. move files from selected theme directory to chrome.
    }else{
      System.out.println("theme could not be changed sorry! (old files need to be deleted to change theme)");
    }
  }

  public static void read_saved_data() {
    try {
      FileReader read = new FileReader("firefox-theme-config.txt"); 
      BufferedReader bufRead = new BufferedReader(read);

      String line = null;
      while ((line = bufRead.readLine()) != null) {
        saved_data.add(line);
      }
      
      for (int i=0; i<saved_data.size(); i++) {
        System.out.println("\t"+saved_data.get(i));
      }
      System.out.println();

    } catch (IOException e) {
      System.out.println("error!: \"firefox-theme-config.txt\" file does not exist :(");
      System.out.println("would you like to create a config file? [y/n]");
      
      String user_choice= scnr.nextLine();
    
      if (user_choice.toUpperCase().equals("Y")) {
        System.out.println("okay! attempting to create config...");
        
        // create new text file and then run them through set up
        create_new_config();
        read_saved_data();
      
      } else {
        System.out.println("okay! exiting program.");
        System.exit(0);
      }
    }
  }

  public static void parse_saved_data() {
    // index 0 = current-theme 
    // index 1 = firefox-theme-path
    // index 2 = firefox-chrome-folder-path
    String currentLine = null;
    char tempChar; 
    String trimmed = null;
    int index = 0;
    
    for (int i=0; i<saved_data.size(); i++) {
      StringBuilder str = new StringBuilder();

      currentLine = saved_data.get(i);
      // find index of first " within currentLine
      index = currentLine.indexOf("\"");

      // trim everything in front of x
      trimmed = currentLine.substring(index+1);
      for (int j=0; j<trimmed.length(); j++) {
        
        tempChar = trimmed.charAt(j);
        if (tempChar == '\"') {
          parsed_saved_data.add(str.toString());
          continue;
        }else{
          str.append(tempChar);
        }
      }
    }
  }

  // reads config and updates variables used in program.
  public static void update_data() {
    // save the selected theme in config to current_theme. 
    current_theme = parsed_saved_data.get(0);
    // save the firefox-themes path in config to themes_directory.
    themes_directory = parsed_saved_data.get(1);
    // save the firefox-chrome-folder-path in config to firefox_chrome_directory. 
    firefox_chrome_directory = parsed_saved_data.get(2);
  }
  
  public static void create_new_config() {
    // this basically just allows the user to set up the config easily.
    // will ask for paths and whatnot
    //
    try {
      PrintWriter writer = new PrintWriter("firefox-theme-config.txt", "UTF-8");
      
      // i suppose we can just initialize as 0 right????????????????????
      writer.println("current-theme: \"0\"");
     
      System.out.println("please enter the path where you will store your firefox themes:\n");
      String firefox_theme_path = scnr.nextLine();

      writer.println("firefox-theme-path: \""+ firefox_theme_path + "\"");

      System.out.println("please enter the path to your firefox chrome folder:\n");
      String firefox_chrome_folder_path = scnr.nextLine();

      writer.println("firefox-chrome-folder-path: \""+ firefox_chrome_folder_path + "\"");
      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void change_themes_path() {
    String clear = scnr.nextLine();
    String firefox_theme_path;
      while (true) {
        System.out.println("\nplease enter the path where you will store your firefox themes:\n");
        firefox_theme_path = scnr.nextLine();
        if (firefox_theme_path.contains("/")) {
          break;
        }else{
          System.out.println("error: please enter a valid path!");
        }
      }
      update_config_file("firefox-theme-path", firefox_theme_path);
  }

  // dataToChange options include:
  //    current-theme
  //    firefox-theme-path
  //    firefox-chrome-folder-path
  //
  //  newData (can be PATH or THEME NUMBER):
  //    what you would like to replace between "....."
  //    so,
  //      "old path or old theme number" -> "new path or theme number"
  //
  public static void update_config_file(String dataToChange, String newData) {
    String newFile = "temp.txt";
    int line_to_replace = 0;
    String newLine = ""; 
    // assigns line to replace depending on input.
    switch (dataToChange) {
      case "current-theme":
        line_to_replace = 0;
        newLine = "current-theme: \"" +newData+ "\"";
        break;

      case "firefox-theme-path":
        line_to_replace = 1;
        newLine = "firefox-theme-path: \"" +newData+ "\"";
        break;

      case "firefox-chrome-folder-path":
        line_to_replace = 2;
        newLine = "firefox-chrome-folder-path: \"" +newData+ "\"";
        break;
    }

    try {
      File inputFile = new File("firefox-theme-config.txt");
      File tempFile = new File(newFile);
      
      BufferedReader reader = new BufferedReader(new FileReader(inputFile));
      BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
       
      int current_line_number = 0;
      String currentLine = "";

      while((currentLine = reader.readLine()) != null) {
        String trimmedLine = currentLine.trim();

        if (current_line_number == line_to_replace) {
          writer.write(newLine);
          writer.newLine();
          current_line_number++;
          continue;
        }
        
        //writer.write(currentLine + System.getProperty("line.seperator"));
        writer.write(currentLine + "\n"); 
        current_line_number++;
      }

      writer.close();
      reader.close();
      inputFile.delete();
      tempFile.renameTo(inputFile);

      update_data();
    } catch (Exception e) {
        //TODO: handle exception
        e.printStackTrace();
      }
    
  }

  public static void change_firefox_chrome_path() {
      String firefox_chrome_folder_path = scnr.nextLine(); 
      // this will be the new way to set it up.
      while (true) {

        System.out.println("\nplease enter the path to your firefox chrome folder:\n");
        firefox_chrome_folder_path = scnr.nextLine();
        if (firefox_chrome_folder_path.contains("/")) {
          break;
        }else{
          System.out.println("error: please enter a valid path!");
        }
      }  
      update_config_file("firefox-chrome-folder-path", firefox_chrome_folder_path);
  }


  public static void main(String[] args) {
    int mode = 0;

    // re-reads all data every loop.
    while (true) {
      initialize_global_variables(); 

      // display ascii art. 
      print_header();
    
      // read & parse data from config.
      System.out.println("reading config file...\n");
      read_saved_data();
      parse_saved_data();
      System.out.println("config successfully read!\n");
      // set global variables to the ones found in the config.
    
      // read saved themes and print them.
      update_data();
      read_themes_folder(themes_directory);    

      System.out.println("theme selected: "+parsed_saved_data.get(0)+". "+theme_names.get(Integer.parseInt(parsed_saved_data.get(0)))+"\n");

      print_current_available_themes();
    
      System.out.println("1. change theme\t2. change themes path\t3. change firefox chrome path\t4. exit\n");    

    
      mode = scnr.nextInt();
      switch(mode) {
        case 1:
          change_theme();
          break;
        case 2:
          change_themes_path();
          break;
        case 3:
          change_firefox_chrome_path();
          break;
        case 4:
          System.exit(0);
      }
    } 
  }
}
