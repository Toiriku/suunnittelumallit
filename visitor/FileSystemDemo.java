public class FileSystemDemo {
    public static void main(String[] args) {
        System.out.println("=== File System Visitor Pattern Demo ===\n");
        
        Directory root = createSampleFileSystem();
        
        demonstrateSizeCalculator(root);
        
        demonstrateSearch(root);
    }
    
    private static Directory createSampleFileSystem() {
        System.out.println("Creating sample file system structure...\n");
        
        Directory root = new Directory("root");
        
        root.addChild(new File("readme.txt", 0.5));
        root.addChild(new File("config.xml", 0.2));
        root.addChild(new File("app.jar", 15.3));
        
        Directory documents = new Directory("documents");
        documents.addChild(new File("report.pdf", 2.1));
        documents.addChild(new File("presentation.pptx", 8.7));
        documents.addChild(new File("data.xlsx", 1.4));
        documents.addChild(new File("notes.txt", 0.1));
        root.addChild(documents);
        
        Directory projects = new Directory("projects");
        root.addChild(projects);
        
        Directory javaProject = new Directory("java-project");
        javaProject.addChild(new File("Main.java", 0.02));
        javaProject.addChild(new File("Utils.java", 0.015));
        javaProject.addChild(new File("config.properties", 0.001));
        projects.addChild(javaProject);
        
        Directory webProject = new Directory("web-project");
        webProject.addChild(new File("index.html", 0.03));
        webProject.addChild(new File("style.css", 0.02));
        webProject.addChild(new File("script.js", 0.05));
        webProject.addChild(new File("logo.png", 0.8));
        projects.addChild(webProject);
        
        Directory media = new Directory("media");
        media.addChild(new File("photo1.jpg", 3.2));
        media.addChild(new File("photo2.jpg", 2.8));
        media.addChild(new File("video.mp4", 45.6));
        media.addChild(new File("music.mp3", 4.1));
        root.addChild(media);
        
        System.out.println("Sample file system created successfully!\n");
        return root;
    }
    
    private static void demonstrateSizeCalculator(Directory root) {
        System.out.println("=== Size Calculator Visitor Demo ===");
        
        SizeCalculatorVisitor sizeCalculator = new SizeCalculatorVisitor();
        root.accept(sizeCalculator);
        
        System.out.println("File system analysis results:");
        System.out.println("- " + sizeCalculator.getSummary());
        System.out.println("- Average file size: " + 
                         String.format("%.2f MB", sizeCalculator.getTotalSize() / sizeCalculator.getFileCount()));
        System.out.println();
    }
    
    private static void demonstrateSearch(Directory root) {
        System.out.println("=== Search Visitor Demo ===");
        
        System.out.println("1. Searching for Java files:");
        SearchVisitor javaSearch = SearchVisitor.byExtension("java");
        root.accept(javaSearch);
        displaySearchResults(javaSearch);
        
        System.out.println("2. Searching for files containing 'config':");
        SearchVisitor configSearch = SearchVisitor.byNamePattern("config");
        root.accept(configSearch);
        displaySearchResults(configSearch);
        
        System.out.println("3. Searching for large files (> 5MB):");
        SearchVisitor largeFileSearch = SearchVisitor.byMinSize(5.0);
        root.accept(largeFileSearch);
        displaySearchResults(largeFileSearch);
        
        System.out.println("4. Searching for small files (< 0.1MB):");
        SearchVisitor smallFileSearch = SearchVisitor.byMaxSize(0.1);
        root.accept(smallFileSearch);
        displaySearchResults(smallFileSearch);
        
        System.out.println("5. Custom search - PDF or image files:");
        SearchVisitor mediaSearch = new SearchVisitor(
            file -> {
                String ext = file.getExtension();
                return ext.equals("pdf") || ext.equals("jpg") || ext.equals("png");
            },
            "PDF or image files"
        );
        root.accept(mediaSearch);
        displaySearchResults(mediaSearch);
    }
    
    private static void displaySearchResults(SearchVisitor searchVisitor) {
        System.out.println("   " + searchVisitor.getSummary());
        if (searchVisitor.getMatchCount() > 0) {
            for (File file : searchVisitor.getMatchingFiles()) {
                System.out.println("   - " + file);
            }
        }
        System.out.println();
    }
}