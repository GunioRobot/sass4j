package com.organicelement.sass4j.maven;

import com.organicelement.sass4j.Sass4Java;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.compiler.util.scan.InclusionScanException;
import org.codehaus.plexus.compiler.util.scan.StaleSourceScanner;
import org.codehaus.plexus.compiler.util.scan.mapping.SuffixMapping;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @goal process
 * @phase generate-resources
 */
public class Sass4jMojo
    extends AbstractMojo {


    /**
     * the source directory to convert.
     * 
     * @parameter default-value="${sourceDirectory}"
     */
    private String sourceDirectory;

    /**
     * the target directory for generated css files.
     *
     * @parameter default-value="${targetDirectory}"
     */
    private String targetDirectory;

    /**
     * load paths for imported or included sass files.
     *
     * @parameter default-value="${sourceDirectory}"
     */
    private String[] loadPaths;

    /**
     * reference to maven project for internal use.
     *
     * @parameter expression="${project}"
     * @required
     * @readOnly true
     */
    protected MavenProject project;

    public void execute() throws MojoExecutionException, MojoFailureException {
        if (loadPaths.length == 0) {
            loadPaths = new String[] {this.sourceDirectory};
        }

        Set<String> includes = new HashSet<String>( 2 );
        includes.add( "**/*.scss" );
        includes.add( "**/*.sass" );

        StaleSourceScanner scanner = new StaleSourceScanner( 0, includes, Collections.EMPTY_SET );
        scanner.addSourceMapping( new SuffixMapping( ".scss", ".css" ) );
        scanner.addSourceMapping( new SuffixMapping( ".sass", ".css" ) );

        Set<File> staleFiles = Collections.emptySet();
        try
        {
            staleFiles = (Set<File>) scanner.getIncludedSources( new File(this.sourceDirectory), new File(this.targetDirectory) );
        }
        catch ( InclusionScanException e )
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if (staleFiles.size() > 0) {
            Sass4Java sassy = new Sass4Java();

            File target = new File(targetDirectory);
            if (!target.exists()) {
                target.mkdirs();
            }
            for (File file : staleFiles) {
                // Ignore partials
                if (!file.getName().startsWith( "_", 0 )) {
                    getLog().info( "Compiling " + file.getName() + " to " + targetDirectory.toString() ) ;
                    sassy.compile_file(file.toURI().toString(), targetDirectory + File.separator + file.getName().substring(0, file.getName().lastIndexOf('.')) + ".css", loadPaths);
                }
            }
            getLog().info( "Finished processing Sass files" );
        } else {
            getLog().info( "All Sass files are up to date" );
        }
    }

}
