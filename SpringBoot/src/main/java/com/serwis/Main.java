package com.serwis;/**
 * Created by jakub on 27.02.2018.
 */

import com.serwis.config.StageManager;
import com.serwis.view.FxmlView;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.serwis.repository")
public class Main extends Application {

	private ConfigurableApplicationContext springContext;
	private StageManager stageManager;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() throws Exception{
		springContext = springBootApplicationContext();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
			stageManager = springContext.getBean(StageManager.class, primaryStage);
			displayInitialScene();
	}

	@Override
	public void stop() throws Exception{
		springContext.close();
	}

	protected void displayInitialScene(){
		stageManager.switchScene(FxmlView.LOGIN);
	}

	private ConfigurableApplicationContext springBootApplicationContext(){
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
		String[] args = getParameters().getRaw().stream().toArray(String[]::new);
		return builder.run(args);
	}

}