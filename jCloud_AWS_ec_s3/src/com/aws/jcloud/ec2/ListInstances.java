package com.aws.jcloud.ec2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jclouds.ContextBuilder;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.ComputeServiceContext;
import org.jclouds.compute.domain.ComputeMetadata;
import org.jclouds.logging.slf4j.config.SLF4JLoggingModule;
import org.jclouds.rest.config.SetCaller.Module;
import org.jclouds.sshj.config.SshjSshClientModule;

import com.google.common.collect.ImmutableSet;

public class ListInstances {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//credentials.properties file has the keys to authenticate to aws
		Properties prop = new Properties();
		InputStream input = null;
		input = new FileInputStream("credentials.properties");
		// load a properties file
		prop.load(input);

		//fetching the access and secret access keys from the properties file
		String identity = prop.getProperty("accessKey");
		String credential = prop.getProperty("secretAccessKey");

		ComputeServiceContext context = ContextBuilder.newBuilder("aws-ec2")
                .credentials(identity, credential)
                .buildView(ComputeServiceContext.class);

		ComputeService client = context.getComputeService();

		System.out.println(client.listNodes().size());
		
		/*
		for (ComputeMetadata node : client.listNodes()) {
			   System.out.println("id : "+node.getId()+"providerid : "+node.getProviderId()+"name : "+node.getName()+"location : "+node.getLocation());
			   }*/
	}

}
