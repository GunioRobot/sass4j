package com.organicelement.sass4j.maven;

import com.organicelement.sass4j.SassJava;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

/**
 * @goal process
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
     * the source directory to convert.
     *
     * @parameter default-value="${targetDirectory}"
     */
    private String targetDirectory;

    /**
     * reference to maven project for internal use.
     *
     * @parameter expression="${project}"
     * @required
     * @readOnly true
     */
    protected MavenProject project;

    protected Log logger;

    public void execute() throws MojoExecutionException, MojoFailureException {
        this.logger = getLog();
        if (sourceDirectory != null && targetDirectory != null) {
            getLog().info( sourceDirectory + targetDirectory );
            SassJava.main(new String[]{"--update", "--no-cache", sourceDirectory.toString() + ":" + targetDirectory.toString()} );
        }
    }

}
