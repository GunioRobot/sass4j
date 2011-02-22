package com.organicelement.sass4j.maven;

import com.organicelement.sass4j.SassJava;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.apache.maven.repository.RepositorySystem;
import org.codehaus.classworlds.ClassRealm;

import java.io.File;


//public abstract class Sass4jMojo extends AbstractMojo {

/**
 * @goal process
 */
public class Sass4jMojo
    extends AbstractMojo {

    private static String DEFAULT_JRUBY_VERSION = "1.5.6";

    public static final String GEM_RUBY_COMMAND = "META-INF/jruby.home/bin/gem";

    public static final String IRB_RUBY_COMMAND = "jirb";

    public static final String IRB_SWING_RUBY_COMMAND = "jirb_swing";

    public static final String RAKE_RUBY_COMMAND = "META-INF/jruby.home/bin/rake";

    /**
     * common arguments
     * 
     * @parameter expression="${args}"
     */
    protected String args;

    /**
     * arguments for the jruby command.
     * 
     * @parameter default-value="${jruby.args}"
     */
//    protected String jrubyArgs = null;

 
    /**
     * arguments for the jruby command.
     * 
     * @parameter expression="${jruby.jvmargs}"
     */
//    protected String jrubyJvmArgs;
   
    /**
     * if the pom.xml has no runtime dependency to a jruby-complete.jar then
     * this version is used to resolve the jruby-complete dependency from the
     * local/remote maven repository. defaults to "1.5.6".
     * 
     * @parameter default-value="${jruby.version}"
     */
//    protected String jrubyVersion;

    /**
     * fork the JRuby execution.
     * 
     * @parameter expression="${jruby.fork}" default-value="true"
     */
//    protected boolean jrubyFork;

    /**
     * verbose jruby related output
     * 
     * @parameter expression="${jruby.verbose}" default-value="false"
     */
//    protected boolean jrubyVerbose;

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

    /**
     * local repository for internal use.
     * 
     * @parameter default-value="${localRepository}"
     * @required
     * @readonly
     */
    protected ArtifactRepository localRepository;

    /**
     * classrealm for internal use.
     * 
     * @parameter expression="${dummyExpression}"
     * @readonly
     */
    protected ClassRealm classRealm;

    /** @component */
    protected RepositorySystem repositorySystem;

    protected Log logger;

    public void execute() throws MojoExecutionException, MojoFailureException {
//        this.logger = new MojoLogger(this.jrubyVerbose, getLog());
        this.logger = getLog();
        if (sourceDirectory != null && targetDirectory != null) {
            getLog().info( sourceDirectory + targetDirectory );
            SassJava.main(new String[]{"--update", "--no-cache", sourceDirectory.toString() + ":" + targetDirectory.toString()} );
        }
        // Do the stuff here
    }

}
