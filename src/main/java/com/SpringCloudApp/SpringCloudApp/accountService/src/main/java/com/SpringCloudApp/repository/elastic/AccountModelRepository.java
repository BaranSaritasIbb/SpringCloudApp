package com.SpringCloudApp.repository.elastic;

import com.SpringCloudApp.model.elastic.AccountModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AccountModelRepository extends ElasticsearchRepository<AccountModel,String> {
}
