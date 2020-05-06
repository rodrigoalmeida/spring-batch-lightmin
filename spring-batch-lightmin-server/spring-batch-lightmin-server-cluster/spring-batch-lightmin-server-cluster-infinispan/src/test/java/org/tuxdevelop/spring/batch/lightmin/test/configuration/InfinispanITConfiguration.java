package org.tuxdevelop.spring.batch.lightmin.test.configuration;

import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tuxdevelop.spring.batch.lightmin.server.cluster.annotation.EnableServerClusterInfinispan;
import org.tuxdevelop.spring.batch.lightmin.server.scheduler.repository.CleanUpRepository;
import org.tuxdevelop.spring.batch.lightmin.server.scheduler.repository.DefaultCleanUpRepository;
import org.tuxdevelop.spring.batch.lightmin.server.scheduler.repository.SchedulerConfigurationRepository;
import org.tuxdevelop.spring.batch.lightmin.server.scheduler.repository.SchedulerExecutionRepository;
import org.tuxdevelop.spring.batch.lightmin.server.service.JobServerService;

@Configuration
@EnableServerClusterInfinispan
public class InfinispanITConfiguration {

    @MockBean
    private JobServerService jobServerService;

    @Bean
    public GlobalConfiguration globalConfiguration() {
        return new GlobalConfigurationBuilder()
                .clusteredDefault()
                .build();
    }

    @Bean
    public CleanUpRepository cleanUpRepository(final SchedulerConfigurationRepository schedulerConfigurationRepository,
                                               final SchedulerExecutionRepository schedulerExecutionRepository) {
        return new DefaultCleanUpRepository(schedulerConfigurationRepository, schedulerExecutionRepository);
    }
}
