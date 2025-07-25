package org.example.bug_in_qbe;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface EnclosingClassRepository extends CrudRepository<EnclosingClass, Long>,
    QueryByExampleExecutor<EnclosingClass> {

}
