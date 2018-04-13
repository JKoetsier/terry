package nl.jkoetsier.uva.dbbench.input.schema.tsql;

import nl.jkoetsier.uva.dbbench.datamodel.DataModel;
import nl.jkoetsier.uva.dbbench.datamodel.Entity;
import nl.jkoetsier.uva.dbbench.datamodel.fields.*;
import nl.jkoetsier.uva.dbbench.parser.tsql.grammar.TSqlParser;
import nl.jkoetsier.uva.dbbench.parser.tsql.grammar.TSqlParserListener;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class TSqlSchemaParserListener implements TSqlParserListener {

    DataModel dataModel = new DataModel();

    @Override
    public void enterTsql_file(TSqlParser.Tsql_fileContext ctx) {
    }

    @Override
    public void exitTsql_file(TSqlParser.Tsql_fileContext ctx) {

    }

    @Override
    public void enterBatch(TSqlParser.BatchContext ctx) {

    }

    @Override
    public void exitBatch(TSqlParser.BatchContext ctx) {

    }

    @Override
    public void enterSql_clauses(TSqlParser.Sql_clausesContext ctx) {
    }

    @Override
    public void exitSql_clauses(TSqlParser.Sql_clausesContext ctx) {

    }

    @Override
    public void enterSql_clause(TSqlParser.Sql_clauseContext ctx) {

    }

    @Override
    public void exitSql_clause(TSqlParser.Sql_clauseContext ctx) {

    }

    @Override
    public void enterDml_clause(TSqlParser.Dml_clauseContext ctx) {

    }

    @Override
    public void exitDml_clause(TSqlParser.Dml_clauseContext ctx) {

    }

    @Override
    public void enterDdl_clause(TSqlParser.Ddl_clauseContext ctx) {
    }

    @Override
    public void exitDdl_clause(TSqlParser.Ddl_clauseContext ctx) {

    }

    @Override
    public void enterBackup_statement(TSqlParser.Backup_statementContext ctx) {

    }

    @Override
    public void exitBackup_statement(TSqlParser.Backup_statementContext ctx) {

    }

    @Override
    public void enterCfl_statement(TSqlParser.Cfl_statementContext ctx) {

    }

    @Override
    public void exitCfl_statement(TSqlParser.Cfl_statementContext ctx) {

    }

    @Override
    public void enterBlock_statement(TSqlParser.Block_statementContext ctx) {

    }

    @Override
    public void exitBlock_statement(TSqlParser.Block_statementContext ctx) {

    }

    @Override
    public void enterBreak_statement(TSqlParser.Break_statementContext ctx) {

    }

    @Override
    public void exitBreak_statement(TSqlParser.Break_statementContext ctx) {

    }

    @Override
    public void enterContinue_statement(TSqlParser.Continue_statementContext ctx) {

    }

    @Override
    public void exitContinue_statement(TSqlParser.Continue_statementContext ctx) {

    }

    @Override
    public void enterGoto_statement(TSqlParser.Goto_statementContext ctx) {

    }

    @Override
    public void exitGoto_statement(TSqlParser.Goto_statementContext ctx) {

    }

    @Override
    public void enterReturn_statement(TSqlParser.Return_statementContext ctx) {

    }

    @Override
    public void exitReturn_statement(TSqlParser.Return_statementContext ctx) {

    }

    @Override
    public void enterIf_statement(TSqlParser.If_statementContext ctx) {

    }

    @Override
    public void exitIf_statement(TSqlParser.If_statementContext ctx) {

    }

    @Override
    public void enterThrow_statement(TSqlParser.Throw_statementContext ctx) {

    }

    @Override
    public void exitThrow_statement(TSqlParser.Throw_statementContext ctx) {

    }

    @Override
    public void enterThrow_error_number(TSqlParser.Throw_error_numberContext ctx) {

    }

    @Override
    public void exitThrow_error_number(TSqlParser.Throw_error_numberContext ctx) {

    }

    @Override
    public void enterThrow_message(TSqlParser.Throw_messageContext ctx) {

    }

    @Override
    public void exitThrow_message(TSqlParser.Throw_messageContext ctx) {

    }

    @Override
    public void enterThrow_state(TSqlParser.Throw_stateContext ctx) {

    }

    @Override
    public void exitThrow_state(TSqlParser.Throw_stateContext ctx) {

    }

    @Override
    public void enterTry_catch_statement(TSqlParser.Try_catch_statementContext ctx) {

    }

    @Override
    public void exitTry_catch_statement(TSqlParser.Try_catch_statementContext ctx) {

    }

    @Override
    public void enterWaitfor_statement(TSqlParser.Waitfor_statementContext ctx) {

    }

    @Override
    public void exitWaitfor_statement(TSqlParser.Waitfor_statementContext ctx) {

    }

    @Override
    public void enterWhile_statement(TSqlParser.While_statementContext ctx) {

    }

    @Override
    public void exitWhile_statement(TSqlParser.While_statementContext ctx) {

    }

    @Override
    public void enterPrint_statement(TSqlParser.Print_statementContext ctx) {

    }

    @Override
    public void exitPrint_statement(TSqlParser.Print_statementContext ctx) {

    }

    @Override
    public void enterRaiseerror_statement(TSqlParser.Raiseerror_statementContext ctx) {

    }

    @Override
    public void exitRaiseerror_statement(TSqlParser.Raiseerror_statementContext ctx) {

    }

    @Override
    public void enterEmpty_statement(TSqlParser.Empty_statementContext ctx) {

    }

    @Override
    public void exitEmpty_statement(TSqlParser.Empty_statementContext ctx) {

    }

    @Override
    public void enterAnother_statement(TSqlParser.Another_statementContext ctx) {

    }

    @Override
    public void exitAnother_statement(TSqlParser.Another_statementContext ctx) {

    }

    @Override
    public void enterAlter_application_role(TSqlParser.Alter_application_roleContext ctx) {

    }

    @Override
    public void exitAlter_application_role(TSqlParser.Alter_application_roleContext ctx) {

    }

    @Override
    public void enterCreate_application_role(TSqlParser.Create_application_roleContext ctx) {

    }

    @Override
    public void exitCreate_application_role(TSqlParser.Create_application_roleContext ctx) {

    }

    @Override
    public void enterDrop_aggregate(TSqlParser.Drop_aggregateContext ctx) {

    }

    @Override
    public void exitDrop_aggregate(TSqlParser.Drop_aggregateContext ctx) {

    }

    @Override
    public void enterDrop_application_role(TSqlParser.Drop_application_roleContext ctx) {

    }

    @Override
    public void exitDrop_application_role(TSqlParser.Drop_application_roleContext ctx) {

    }

    @Override
    public void enterAlter_assembly(TSqlParser.Alter_assemblyContext ctx) {

    }

    @Override
    public void exitAlter_assembly(TSqlParser.Alter_assemblyContext ctx) {

    }

    @Override
    public void enterAlter_assembly_start(TSqlParser.Alter_assembly_startContext ctx) {

    }

    @Override
    public void exitAlter_assembly_start(TSqlParser.Alter_assembly_startContext ctx) {

    }

    @Override
    public void enterAlter_assembly_clause(TSqlParser.Alter_assembly_clauseContext ctx) {

    }

    @Override
    public void exitAlter_assembly_clause(TSqlParser.Alter_assembly_clauseContext ctx) {

    }

    @Override
    public void enterAlter_assembly_from_clause(TSqlParser.Alter_assembly_from_clauseContext ctx) {

    }

    @Override
    public void exitAlter_assembly_from_clause(TSqlParser.Alter_assembly_from_clauseContext ctx) {

    }

    @Override
    public void enterAlter_assembly_from_clause_start(TSqlParser.Alter_assembly_from_clause_startContext ctx) {

    }

    @Override
    public void exitAlter_assembly_from_clause_start(TSqlParser.Alter_assembly_from_clause_startContext ctx) {

    }

    @Override
    public void enterAlter_assembly_drop_clause(TSqlParser.Alter_assembly_drop_clauseContext ctx) {

    }

    @Override
    public void exitAlter_assembly_drop_clause(TSqlParser.Alter_assembly_drop_clauseContext ctx) {

    }

    @Override
    public void enterAlter_assembly_drop_multiple_files(TSqlParser.Alter_assembly_drop_multiple_filesContext ctx) {

    }

    @Override
    public void exitAlter_assembly_drop_multiple_files(TSqlParser.Alter_assembly_drop_multiple_filesContext ctx) {

    }

    @Override
    public void enterAlter_assembly_drop(TSqlParser.Alter_assembly_dropContext ctx) {

    }

    @Override
    public void exitAlter_assembly_drop(TSqlParser.Alter_assembly_dropContext ctx) {

    }

    @Override
    public void enterAlter_assembly_add_clause(TSqlParser.Alter_assembly_add_clauseContext ctx) {

    }

    @Override
    public void exitAlter_assembly_add_clause(TSqlParser.Alter_assembly_add_clauseContext ctx) {

    }

    @Override
    public void enterAlter_asssembly_add_clause_start(TSqlParser.Alter_asssembly_add_clause_startContext ctx) {

    }

    @Override
    public void exitAlter_asssembly_add_clause_start(TSqlParser.Alter_asssembly_add_clause_startContext ctx) {

    }

    @Override
    public void enterAlter_assembly_client_file_clause(TSqlParser.Alter_assembly_client_file_clauseContext ctx) {

    }

    @Override
    public void exitAlter_assembly_client_file_clause(TSqlParser.Alter_assembly_client_file_clauseContext ctx) {

    }

    @Override
    public void enterAlter_assembly_file_name(TSqlParser.Alter_assembly_file_nameContext ctx) {

    }

    @Override
    public void exitAlter_assembly_file_name(TSqlParser.Alter_assembly_file_nameContext ctx) {

    }

    @Override
    public void enterAlter_assembly_file_bits(TSqlParser.Alter_assembly_file_bitsContext ctx) {

    }

    @Override
    public void exitAlter_assembly_file_bits(TSqlParser.Alter_assembly_file_bitsContext ctx) {

    }

    @Override
    public void enterAlter_assembly_as(TSqlParser.Alter_assembly_asContext ctx) {

    }

    @Override
    public void exitAlter_assembly_as(TSqlParser.Alter_assembly_asContext ctx) {

    }

    @Override
    public void enterAlter_assembly_with_clause(TSqlParser.Alter_assembly_with_clauseContext ctx) {

    }

    @Override
    public void exitAlter_assembly_with_clause(TSqlParser.Alter_assembly_with_clauseContext ctx) {

    }

    @Override
    public void enterAlter_assembly_with(TSqlParser.Alter_assembly_withContext ctx) {

    }

    @Override
    public void exitAlter_assembly_with(TSqlParser.Alter_assembly_withContext ctx) {

    }

    @Override
    public void enterClient_assembly_specifier(TSqlParser.Client_assembly_specifierContext ctx) {

    }

    @Override
    public void exitClient_assembly_specifier(TSqlParser.Client_assembly_specifierContext ctx) {

    }

    @Override
    public void enterAssembly_option(TSqlParser.Assembly_optionContext ctx) {

    }

    @Override
    public void exitAssembly_option(TSqlParser.Assembly_optionContext ctx) {

    }

    @Override
    public void enterNetwork_file_share(TSqlParser.Network_file_shareContext ctx) {

    }

    @Override
    public void exitNetwork_file_share(TSqlParser.Network_file_shareContext ctx) {

    }

    @Override
    public void enterNetwork_computer(TSqlParser.Network_computerContext ctx) {

    }

    @Override
    public void exitNetwork_computer(TSqlParser.Network_computerContext ctx) {

    }

    @Override
    public void enterNetwork_file_start(TSqlParser.Network_file_startContext ctx) {

    }

    @Override
    public void exitNetwork_file_start(TSqlParser.Network_file_startContext ctx) {

    }

    @Override
    public void enterFile_path(TSqlParser.File_pathContext ctx) {

    }

    @Override
    public void exitFile_path(TSqlParser.File_pathContext ctx) {

    }

    @Override
    public void enterFile_directory_path_separator(TSqlParser.File_directory_path_separatorContext ctx) {

    }

    @Override
    public void exitFile_directory_path_separator(TSqlParser.File_directory_path_separatorContext ctx) {

    }

    @Override
    public void enterLocal_file(TSqlParser.Local_fileContext ctx) {

    }

    @Override
    public void exitLocal_file(TSqlParser.Local_fileContext ctx) {

    }

    @Override
    public void enterLocal_drive(TSqlParser.Local_driveContext ctx) {

    }

    @Override
    public void exitLocal_drive(TSqlParser.Local_driveContext ctx) {

    }

    @Override
    public void enterMultiple_local_files(TSqlParser.Multiple_local_filesContext ctx) {

    }

    @Override
    public void exitMultiple_local_files(TSqlParser.Multiple_local_filesContext ctx) {

    }

    @Override
    public void enterMultiple_local_file_start(TSqlParser.Multiple_local_file_startContext ctx) {

    }

    @Override
    public void exitMultiple_local_file_start(TSqlParser.Multiple_local_file_startContext ctx) {

    }

    @Override
    public void enterCreate_assembly(TSqlParser.Create_assemblyContext ctx) {

    }

    @Override
    public void exitCreate_assembly(TSqlParser.Create_assemblyContext ctx) {

    }

    @Override
    public void enterDrop_assembly(TSqlParser.Drop_assemblyContext ctx) {

    }

    @Override
    public void exitDrop_assembly(TSqlParser.Drop_assemblyContext ctx) {

    }

    @Override
    public void enterAlter_asymmetric_key(TSqlParser.Alter_asymmetric_keyContext ctx) {

    }

    @Override
    public void exitAlter_asymmetric_key(TSqlParser.Alter_asymmetric_keyContext ctx) {

    }

    @Override
    public void enterAlter_asymmetric_key_start(TSqlParser.Alter_asymmetric_key_startContext ctx) {

    }

    @Override
    public void exitAlter_asymmetric_key_start(TSqlParser.Alter_asymmetric_key_startContext ctx) {

    }

    @Override
    public void enterAsymmetric_key_option(TSqlParser.Asymmetric_key_optionContext ctx) {

    }

    @Override
    public void exitAsymmetric_key_option(TSqlParser.Asymmetric_key_optionContext ctx) {

    }

    @Override
    public void enterAsymmetric_key_option_start(TSqlParser.Asymmetric_key_option_startContext ctx) {

    }

    @Override
    public void exitAsymmetric_key_option_start(TSqlParser.Asymmetric_key_option_startContext ctx) {

    }

    @Override
    public void enterAsymmetric_key_password_change_option(TSqlParser.Asymmetric_key_password_change_optionContext ctx) {

    }

    @Override
    public void exitAsymmetric_key_password_change_option(TSqlParser.Asymmetric_key_password_change_optionContext ctx) {

    }

    @Override
    public void enterCreate_asymmetric_key(TSqlParser.Create_asymmetric_keyContext ctx) {

    }

    @Override
    public void exitCreate_asymmetric_key(TSqlParser.Create_asymmetric_keyContext ctx) {

    }

    @Override
    public void enterDrop_asymmetric_key(TSqlParser.Drop_asymmetric_keyContext ctx) {

    }

    @Override
    public void exitDrop_asymmetric_key(TSqlParser.Drop_asymmetric_keyContext ctx) {

    }

    @Override
    public void enterAlter_authorization(TSqlParser.Alter_authorizationContext ctx) {

    }

    @Override
    public void exitAlter_authorization(TSqlParser.Alter_authorizationContext ctx) {

    }

    @Override
    public void enterAuthorization_grantee(TSqlParser.Authorization_granteeContext ctx) {

    }

    @Override
    public void exitAuthorization_grantee(TSqlParser.Authorization_granteeContext ctx) {

    }

    @Override
    public void enterEntity_to(TSqlParser.Entity_toContext ctx) {

    }

    @Override
    public void exitEntity_to(TSqlParser.Entity_toContext ctx) {

    }

    @Override
    public void enterColon_colon(TSqlParser.Colon_colonContext ctx) {

    }

    @Override
    public void exitColon_colon(TSqlParser.Colon_colonContext ctx) {

    }

    @Override
    public void enterAlter_authorization_start(TSqlParser.Alter_authorization_startContext ctx) {

    }

    @Override
    public void exitAlter_authorization_start(TSqlParser.Alter_authorization_startContext ctx) {

    }

    @Override
    public void enterAlter_authorization_for_sql_database(TSqlParser.Alter_authorization_for_sql_databaseContext ctx) {

    }

    @Override
    public void exitAlter_authorization_for_sql_database(TSqlParser.Alter_authorization_for_sql_databaseContext ctx) {

    }

    @Override
    public void enterAlter_authorization_for_azure_dw(TSqlParser.Alter_authorization_for_azure_dwContext ctx) {

    }

    @Override
    public void exitAlter_authorization_for_azure_dw(TSqlParser.Alter_authorization_for_azure_dwContext ctx) {

    }

    @Override
    public void enterAlter_authorization_for_parallel_dw(TSqlParser.Alter_authorization_for_parallel_dwContext ctx) {

    }

    @Override
    public void exitAlter_authorization_for_parallel_dw(TSqlParser.Alter_authorization_for_parallel_dwContext ctx) {

    }

    @Override
    public void enterClass_type(TSqlParser.Class_typeContext ctx) {

    }

    @Override
    public void exitClass_type(TSqlParser.Class_typeContext ctx) {

    }

    @Override
    public void enterClass_type_for_sql_database(TSqlParser.Class_type_for_sql_databaseContext ctx) {

    }

    @Override
    public void exitClass_type_for_sql_database(TSqlParser.Class_type_for_sql_databaseContext ctx) {

    }

    @Override
    public void enterClass_type_for_azure_dw(TSqlParser.Class_type_for_azure_dwContext ctx) {

    }

    @Override
    public void exitClass_type_for_azure_dw(TSqlParser.Class_type_for_azure_dwContext ctx) {

    }

    @Override
    public void enterClass_type_for_parallel_dw(TSqlParser.Class_type_for_parallel_dwContext ctx) {

    }

    @Override
    public void exitClass_type_for_parallel_dw(TSqlParser.Class_type_for_parallel_dwContext ctx) {

    }

    @Override
    public void enterDrop_availability_group(TSqlParser.Drop_availability_groupContext ctx) {

    }

    @Override
    public void exitDrop_availability_group(TSqlParser.Drop_availability_groupContext ctx) {

    }

    @Override
    public void enterAlter_availability_group(TSqlParser.Alter_availability_groupContext ctx) {

    }

    @Override
    public void exitAlter_availability_group(TSqlParser.Alter_availability_groupContext ctx) {

    }

    @Override
    public void enterAlter_availability_group_start(TSqlParser.Alter_availability_group_startContext ctx) {

    }

    @Override
    public void exitAlter_availability_group_start(TSqlParser.Alter_availability_group_startContext ctx) {

    }

    @Override
    public void enterAlter_availability_group_options(TSqlParser.Alter_availability_group_optionsContext ctx) {

    }

    @Override
    public void exitAlter_availability_group_options(TSqlParser.Alter_availability_group_optionsContext ctx) {

    }

    @Override
    public void enterCreate_or_alter_broker_priority(TSqlParser.Create_or_alter_broker_priorityContext ctx) {

    }

    @Override
    public void exitCreate_or_alter_broker_priority(TSqlParser.Create_or_alter_broker_priorityContext ctx) {

    }

    @Override
    public void enterDrop_broker_priority(TSqlParser.Drop_broker_priorityContext ctx) {

    }

    @Override
    public void exitDrop_broker_priority(TSqlParser.Drop_broker_priorityContext ctx) {

    }

    @Override
    public void enterAlter_certificate(TSqlParser.Alter_certificateContext ctx) {

    }

    @Override
    public void exitAlter_certificate(TSqlParser.Alter_certificateContext ctx) {

    }

    @Override
    public void enterAlter_column_encryption_key(TSqlParser.Alter_column_encryption_keyContext ctx) {

    }

    @Override
    public void exitAlter_column_encryption_key(TSqlParser.Alter_column_encryption_keyContext ctx) {

    }

    @Override
    public void enterCreate_column_encryption_key(TSqlParser.Create_column_encryption_keyContext ctx) {

    }

    @Override
    public void exitCreate_column_encryption_key(TSqlParser.Create_column_encryption_keyContext ctx) {

    }

    @Override
    public void enterDrop_certificate(TSqlParser.Drop_certificateContext ctx) {

    }

    @Override
    public void exitDrop_certificate(TSqlParser.Drop_certificateContext ctx) {

    }

    @Override
    public void enterDrop_column_encryption_key(TSqlParser.Drop_column_encryption_keyContext ctx) {

    }

    @Override
    public void exitDrop_column_encryption_key(TSqlParser.Drop_column_encryption_keyContext ctx) {

    }

    @Override
    public void enterDrop_column_master_key(TSqlParser.Drop_column_master_keyContext ctx) {

    }

    @Override
    public void exitDrop_column_master_key(TSqlParser.Drop_column_master_keyContext ctx) {

    }

    @Override
    public void enterDrop_contract(TSqlParser.Drop_contractContext ctx) {

    }

    @Override
    public void exitDrop_contract(TSqlParser.Drop_contractContext ctx) {

    }

    @Override
    public void enterDrop_credential(TSqlParser.Drop_credentialContext ctx) {

    }

    @Override
    public void exitDrop_credential(TSqlParser.Drop_credentialContext ctx) {

    }

    @Override
    public void enterDrop_cryptograhic_provider(TSqlParser.Drop_cryptograhic_providerContext ctx) {

    }

    @Override
    public void exitDrop_cryptograhic_provider(TSqlParser.Drop_cryptograhic_providerContext ctx) {

    }

    @Override
    public void enterDrop_database(TSqlParser.Drop_databaseContext ctx) {

    }

    @Override
    public void exitDrop_database(TSqlParser.Drop_databaseContext ctx) {

    }

    @Override
    public void enterDrop_database_audit_specification(TSqlParser.Drop_database_audit_specificationContext ctx) {

    }

    @Override
    public void exitDrop_database_audit_specification(TSqlParser.Drop_database_audit_specificationContext ctx) {

    }

    @Override
    public void enterDrop_database_scoped_credential(TSqlParser.Drop_database_scoped_credentialContext ctx) {

    }

    @Override
    public void exitDrop_database_scoped_credential(TSqlParser.Drop_database_scoped_credentialContext ctx) {

    }

    @Override
    public void enterDrop_default(TSqlParser.Drop_defaultContext ctx) {

    }

    @Override
    public void exitDrop_default(TSqlParser.Drop_defaultContext ctx) {

    }

    @Override
    public void enterDrop_endpoint(TSqlParser.Drop_endpointContext ctx) {

    }

    @Override
    public void exitDrop_endpoint(TSqlParser.Drop_endpointContext ctx) {

    }

    @Override
    public void enterDrop_external_data_source(TSqlParser.Drop_external_data_sourceContext ctx) {

    }

    @Override
    public void exitDrop_external_data_source(TSqlParser.Drop_external_data_sourceContext ctx) {

    }

    @Override
    public void enterDrop_external_file_format(TSqlParser.Drop_external_file_formatContext ctx) {

    }

    @Override
    public void exitDrop_external_file_format(TSqlParser.Drop_external_file_formatContext ctx) {

    }

    @Override
    public void enterDrop_external_library(TSqlParser.Drop_external_libraryContext ctx) {

    }

    @Override
    public void exitDrop_external_library(TSqlParser.Drop_external_libraryContext ctx) {

    }

    @Override
    public void enterDrop_external_resource_pool(TSqlParser.Drop_external_resource_poolContext ctx) {

    }

    @Override
    public void exitDrop_external_resource_pool(TSqlParser.Drop_external_resource_poolContext ctx) {

    }

    @Override
    public void enterDrop_external_table(TSqlParser.Drop_external_tableContext ctx) {

    }

    @Override
    public void exitDrop_external_table(TSqlParser.Drop_external_tableContext ctx) {

    }

    @Override
    public void enterDrop_event_notifications(TSqlParser.Drop_event_notificationsContext ctx) {

    }

    @Override
    public void exitDrop_event_notifications(TSqlParser.Drop_event_notificationsContext ctx) {

    }

    @Override
    public void enterDrop_event_session(TSqlParser.Drop_event_sessionContext ctx) {

    }

    @Override
    public void exitDrop_event_session(TSqlParser.Drop_event_sessionContext ctx) {

    }

    @Override
    public void enterDrop_fulltext_catalog(TSqlParser.Drop_fulltext_catalogContext ctx) {

    }

    @Override
    public void exitDrop_fulltext_catalog(TSqlParser.Drop_fulltext_catalogContext ctx) {

    }

    @Override
    public void enterDrop_fulltext_index(TSqlParser.Drop_fulltext_indexContext ctx) {

    }

    @Override
    public void exitDrop_fulltext_index(TSqlParser.Drop_fulltext_indexContext ctx) {

    }

    @Override
    public void enterDrop_fulltext_stoplist(TSqlParser.Drop_fulltext_stoplistContext ctx) {

    }

    @Override
    public void exitDrop_fulltext_stoplist(TSqlParser.Drop_fulltext_stoplistContext ctx) {

    }

    @Override
    public void enterDrop_login(TSqlParser.Drop_loginContext ctx) {

    }

    @Override
    public void exitDrop_login(TSqlParser.Drop_loginContext ctx) {

    }

    @Override
    public void enterDrop_master_key(TSqlParser.Drop_master_keyContext ctx) {

    }

    @Override
    public void exitDrop_master_key(TSqlParser.Drop_master_keyContext ctx) {

    }

    @Override
    public void enterDrop_message_type(TSqlParser.Drop_message_typeContext ctx) {

    }

    @Override
    public void exitDrop_message_type(TSqlParser.Drop_message_typeContext ctx) {

    }

    @Override
    public void enterDrop_partition_function(TSqlParser.Drop_partition_functionContext ctx) {

    }

    @Override
    public void exitDrop_partition_function(TSqlParser.Drop_partition_functionContext ctx) {

    }

    @Override
    public void enterDrop_partition_scheme(TSqlParser.Drop_partition_schemeContext ctx) {

    }

    @Override
    public void exitDrop_partition_scheme(TSqlParser.Drop_partition_schemeContext ctx) {

    }

    @Override
    public void enterDrop_queue(TSqlParser.Drop_queueContext ctx) {

    }

    @Override
    public void exitDrop_queue(TSqlParser.Drop_queueContext ctx) {

    }

    @Override
    public void enterDrop_remote_service_binding(TSqlParser.Drop_remote_service_bindingContext ctx) {

    }

    @Override
    public void exitDrop_remote_service_binding(TSqlParser.Drop_remote_service_bindingContext ctx) {

    }

    @Override
    public void enterDrop_resource_pool(TSqlParser.Drop_resource_poolContext ctx) {

    }

    @Override
    public void exitDrop_resource_pool(TSqlParser.Drop_resource_poolContext ctx) {

    }

    @Override
    public void enterDrop_db_role(TSqlParser.Drop_db_roleContext ctx) {

    }

    @Override
    public void exitDrop_db_role(TSqlParser.Drop_db_roleContext ctx) {

    }

    @Override
    public void enterDrop_route(TSqlParser.Drop_routeContext ctx) {

    }

    @Override
    public void exitDrop_route(TSqlParser.Drop_routeContext ctx) {

    }

    @Override
    public void enterDrop_rule(TSqlParser.Drop_ruleContext ctx) {

    }

    @Override
    public void exitDrop_rule(TSqlParser.Drop_ruleContext ctx) {

    }

    @Override
    public void enterDrop_schema(TSqlParser.Drop_schemaContext ctx) {

    }

    @Override
    public void exitDrop_schema(TSqlParser.Drop_schemaContext ctx) {

    }

    @Override
    public void enterDrop_search_property_list(TSqlParser.Drop_search_property_listContext ctx) {

    }

    @Override
    public void exitDrop_search_property_list(TSqlParser.Drop_search_property_listContext ctx) {

    }

    @Override
    public void enterDrop_security_policy(TSqlParser.Drop_security_policyContext ctx) {

    }

    @Override
    public void exitDrop_security_policy(TSqlParser.Drop_security_policyContext ctx) {

    }

    @Override
    public void enterDrop_sequence(TSqlParser.Drop_sequenceContext ctx) {

    }

    @Override
    public void exitDrop_sequence(TSqlParser.Drop_sequenceContext ctx) {

    }

    @Override
    public void enterDrop_server_audit(TSqlParser.Drop_server_auditContext ctx) {

    }

    @Override
    public void exitDrop_server_audit(TSqlParser.Drop_server_auditContext ctx) {

    }

    @Override
    public void enterDrop_server_audit_specification(TSqlParser.Drop_server_audit_specificationContext ctx) {

    }

    @Override
    public void exitDrop_server_audit_specification(TSqlParser.Drop_server_audit_specificationContext ctx) {

    }

    @Override
    public void enterDrop_server_role(TSqlParser.Drop_server_roleContext ctx) {

    }

    @Override
    public void exitDrop_server_role(TSqlParser.Drop_server_roleContext ctx) {

    }

    @Override
    public void enterDrop_service(TSqlParser.Drop_serviceContext ctx) {

    }

    @Override
    public void exitDrop_service(TSqlParser.Drop_serviceContext ctx) {

    }

    @Override
    public void enterDrop_signature(TSqlParser.Drop_signatureContext ctx) {

    }

    @Override
    public void exitDrop_signature(TSqlParser.Drop_signatureContext ctx) {

    }

    @Override
    public void enterDrop_statistics_name_azure_dw_and_pdw(TSqlParser.Drop_statistics_name_azure_dw_and_pdwContext ctx) {

    }

    @Override
    public void exitDrop_statistics_name_azure_dw_and_pdw(TSqlParser.Drop_statistics_name_azure_dw_and_pdwContext ctx) {

    }

    @Override
    public void enterDrop_symmetric_key(TSqlParser.Drop_symmetric_keyContext ctx) {

    }

    @Override
    public void exitDrop_symmetric_key(TSqlParser.Drop_symmetric_keyContext ctx) {

    }

    @Override
    public void enterDrop_synonym(TSqlParser.Drop_synonymContext ctx) {

    }

    @Override
    public void exitDrop_synonym(TSqlParser.Drop_synonymContext ctx) {

    }

    @Override
    public void enterDrop_user(TSqlParser.Drop_userContext ctx) {

    }

    @Override
    public void exitDrop_user(TSqlParser.Drop_userContext ctx) {

    }

    @Override
    public void enterDrop_workload_group(TSqlParser.Drop_workload_groupContext ctx) {

    }

    @Override
    public void exitDrop_workload_group(TSqlParser.Drop_workload_groupContext ctx) {

    }

    @Override
    public void enterDrop_xml_schema_collection(TSqlParser.Drop_xml_schema_collectionContext ctx) {

    }

    @Override
    public void exitDrop_xml_schema_collection(TSqlParser.Drop_xml_schema_collectionContext ctx) {

    }

    @Override
    public void enterDisable_trigger(TSqlParser.Disable_triggerContext ctx) {

    }

    @Override
    public void exitDisable_trigger(TSqlParser.Disable_triggerContext ctx) {

    }

    @Override
    public void enterEnable_trigger(TSqlParser.Enable_triggerContext ctx) {

    }

    @Override
    public void exitEnable_trigger(TSqlParser.Enable_triggerContext ctx) {

    }

    @Override
    public void enterLock_table(TSqlParser.Lock_tableContext ctx) {

    }

    @Override
    public void exitLock_table(TSqlParser.Lock_tableContext ctx) {

    }

    @Override
    public void enterTruncate_table(TSqlParser.Truncate_tableContext ctx) {

    }

    @Override
    public void exitTruncate_table(TSqlParser.Truncate_tableContext ctx) {

    }

    @Override
    public void enterCreate_column_master_key(TSqlParser.Create_column_master_keyContext ctx) {

    }

    @Override
    public void exitCreate_column_master_key(TSqlParser.Create_column_master_keyContext ctx) {

    }

    @Override
    public void enterAlter_credential(TSqlParser.Alter_credentialContext ctx) {

    }

    @Override
    public void exitAlter_credential(TSqlParser.Alter_credentialContext ctx) {

    }

    @Override
    public void enterCreate_credential(TSqlParser.Create_credentialContext ctx) {

    }

    @Override
    public void exitCreate_credential(TSqlParser.Create_credentialContext ctx) {

    }

    @Override
    public void enterAlter_cryptographic_provider(TSqlParser.Alter_cryptographic_providerContext ctx) {

    }

    @Override
    public void exitAlter_cryptographic_provider(TSqlParser.Alter_cryptographic_providerContext ctx) {

    }

    @Override
    public void enterCreate_cryptographic_provider(TSqlParser.Create_cryptographic_providerContext ctx) {

    }

    @Override
    public void exitCreate_cryptographic_provider(TSqlParser.Create_cryptographic_providerContext ctx) {

    }

    @Override
    public void enterCreate_event_notification(TSqlParser.Create_event_notificationContext ctx) {

    }

    @Override
    public void exitCreate_event_notification(TSqlParser.Create_event_notificationContext ctx) {

    }

    @Override
    public void enterCreate_or_alter_event_session(TSqlParser.Create_or_alter_event_sessionContext ctx) {

    }

    @Override
    public void exitCreate_or_alter_event_session(TSqlParser.Create_or_alter_event_sessionContext ctx) {

    }

    @Override
    public void enterEvent_session_predicate_expression(TSqlParser.Event_session_predicate_expressionContext ctx) {

    }

    @Override
    public void exitEvent_session_predicate_expression(TSqlParser.Event_session_predicate_expressionContext ctx) {

    }

    @Override
    public void enterEvent_session_predicate_factor(TSqlParser.Event_session_predicate_factorContext ctx) {

    }

    @Override
    public void exitEvent_session_predicate_factor(TSqlParser.Event_session_predicate_factorContext ctx) {

    }

    @Override
    public void enterEvent_session_predicate_leaf(TSqlParser.Event_session_predicate_leafContext ctx) {

    }

    @Override
    public void exitEvent_session_predicate_leaf(TSqlParser.Event_session_predicate_leafContext ctx) {

    }

    @Override
    public void enterAlter_external_data_source(TSqlParser.Alter_external_data_sourceContext ctx) {

    }

    @Override
    public void exitAlter_external_data_source(TSqlParser.Alter_external_data_sourceContext ctx) {

    }

    @Override
    public void enterAlter_external_library(TSqlParser.Alter_external_libraryContext ctx) {

    }

    @Override
    public void exitAlter_external_library(TSqlParser.Alter_external_libraryContext ctx) {

    }

    @Override
    public void enterCreate_external_library(TSqlParser.Create_external_libraryContext ctx) {

    }

    @Override
    public void exitCreate_external_library(TSqlParser.Create_external_libraryContext ctx) {

    }

    @Override
    public void enterAlter_external_resource_pool(TSqlParser.Alter_external_resource_poolContext ctx) {

    }

    @Override
    public void exitAlter_external_resource_pool(TSqlParser.Alter_external_resource_poolContext ctx) {

    }

    @Override
    public void enterCreate_external_resource_pool(TSqlParser.Create_external_resource_poolContext ctx) {

    }

    @Override
    public void exitCreate_external_resource_pool(TSqlParser.Create_external_resource_poolContext ctx) {

    }

    @Override
    public void enterAlter_fulltext_catalog(TSqlParser.Alter_fulltext_catalogContext ctx) {

    }

    @Override
    public void exitAlter_fulltext_catalog(TSqlParser.Alter_fulltext_catalogContext ctx) {

    }

    @Override
    public void enterCreate_fulltext_catalog(TSqlParser.Create_fulltext_catalogContext ctx) {

    }

    @Override
    public void exitCreate_fulltext_catalog(TSqlParser.Create_fulltext_catalogContext ctx) {

    }

    @Override
    public void enterAlter_fulltext_stoplist(TSqlParser.Alter_fulltext_stoplistContext ctx) {

    }

    @Override
    public void exitAlter_fulltext_stoplist(TSqlParser.Alter_fulltext_stoplistContext ctx) {

    }

    @Override
    public void enterCreate_fulltext_stoplist(TSqlParser.Create_fulltext_stoplistContext ctx) {

    }

    @Override
    public void exitCreate_fulltext_stoplist(TSqlParser.Create_fulltext_stoplistContext ctx) {

    }

    @Override
    public void enterAlter_login_sql_server(TSqlParser.Alter_login_sql_serverContext ctx) {

    }

    @Override
    public void exitAlter_login_sql_server(TSqlParser.Alter_login_sql_serverContext ctx) {

    }

    @Override
    public void enterCreate_login_sql_server(TSqlParser.Create_login_sql_serverContext ctx) {

    }

    @Override
    public void exitCreate_login_sql_server(TSqlParser.Create_login_sql_serverContext ctx) {

    }

    @Override
    public void enterAlter_login_azure_sql(TSqlParser.Alter_login_azure_sqlContext ctx) {

    }

    @Override
    public void exitAlter_login_azure_sql(TSqlParser.Alter_login_azure_sqlContext ctx) {

    }

    @Override
    public void enterCreate_login_azure_sql(TSqlParser.Create_login_azure_sqlContext ctx) {

    }

    @Override
    public void exitCreate_login_azure_sql(TSqlParser.Create_login_azure_sqlContext ctx) {

    }

    @Override
    public void enterAlter_login_azure_sql_dw_and_pdw(TSqlParser.Alter_login_azure_sql_dw_and_pdwContext ctx) {

    }

    @Override
    public void exitAlter_login_azure_sql_dw_and_pdw(TSqlParser.Alter_login_azure_sql_dw_and_pdwContext ctx) {

    }

    @Override
    public void enterCreate_login_pdw(TSqlParser.Create_login_pdwContext ctx) {

    }

    @Override
    public void exitCreate_login_pdw(TSqlParser.Create_login_pdwContext ctx) {

    }

    @Override
    public void enterAlter_master_key_sql_server(TSqlParser.Alter_master_key_sql_serverContext ctx) {

    }

    @Override
    public void exitAlter_master_key_sql_server(TSqlParser.Alter_master_key_sql_serverContext ctx) {

    }

    @Override
    public void enterCreate_master_key_sql_server(TSqlParser.Create_master_key_sql_serverContext ctx) {

    }

    @Override
    public void exitCreate_master_key_sql_server(TSqlParser.Create_master_key_sql_serverContext ctx) {

    }

    @Override
    public void enterAlter_master_key_azure_sql(TSqlParser.Alter_master_key_azure_sqlContext ctx) {

    }

    @Override
    public void exitAlter_master_key_azure_sql(TSqlParser.Alter_master_key_azure_sqlContext ctx) {

    }

    @Override
    public void enterCreate_master_key_azure_sql(TSqlParser.Create_master_key_azure_sqlContext ctx) {

    }

    @Override
    public void exitCreate_master_key_azure_sql(TSqlParser.Create_master_key_azure_sqlContext ctx) {

    }

    @Override
    public void enterAlter_message_type(TSqlParser.Alter_message_typeContext ctx) {

    }

    @Override
    public void exitAlter_message_type(TSqlParser.Alter_message_typeContext ctx) {

    }

    @Override
    public void enterAlter_partition_function(TSqlParser.Alter_partition_functionContext ctx) {

    }

    @Override
    public void exitAlter_partition_function(TSqlParser.Alter_partition_functionContext ctx) {

    }

    @Override
    public void enterAlter_partition_scheme(TSqlParser.Alter_partition_schemeContext ctx) {

    }

    @Override
    public void exitAlter_partition_scheme(TSqlParser.Alter_partition_schemeContext ctx) {

    }

    @Override
    public void enterAlter_remote_service_binding(TSqlParser.Alter_remote_service_bindingContext ctx) {

    }

    @Override
    public void exitAlter_remote_service_binding(TSqlParser.Alter_remote_service_bindingContext ctx) {

    }

    @Override
    public void enterCreate_remote_service_binding(TSqlParser.Create_remote_service_bindingContext ctx) {

    }

    @Override
    public void exitCreate_remote_service_binding(TSqlParser.Create_remote_service_bindingContext ctx) {

    }

    @Override
    public void enterCreate_resource_pool(TSqlParser.Create_resource_poolContext ctx) {

    }

    @Override
    public void exitCreate_resource_pool(TSqlParser.Create_resource_poolContext ctx) {

    }

    @Override
    public void enterAlter_resource_governor(TSqlParser.Alter_resource_governorContext ctx) {

    }

    @Override
    public void exitAlter_resource_governor(TSqlParser.Alter_resource_governorContext ctx) {

    }

    @Override
    public void enterAlter_db_role(TSqlParser.Alter_db_roleContext ctx) {

    }

    @Override
    public void exitAlter_db_role(TSqlParser.Alter_db_roleContext ctx) {

    }

    @Override
    public void enterCreate_db_role(TSqlParser.Create_db_roleContext ctx) {

    }

    @Override
    public void exitCreate_db_role(TSqlParser.Create_db_roleContext ctx) {

    }

    @Override
    public void enterCreate_route(TSqlParser.Create_routeContext ctx) {

    }

    @Override
    public void exitCreate_route(TSqlParser.Create_routeContext ctx) {

    }

    @Override
    public void enterCreate_rule(TSqlParser.Create_ruleContext ctx) {

    }

    @Override
    public void exitCreate_rule(TSqlParser.Create_ruleContext ctx) {

    }

    @Override
    public void enterAlter_schema_sql(TSqlParser.Alter_schema_sqlContext ctx) {

    }

    @Override
    public void exitAlter_schema_sql(TSqlParser.Alter_schema_sqlContext ctx) {

    }

    @Override
    public void enterCreate_schema(TSqlParser.Create_schemaContext ctx) {

    }

    @Override
    public void exitCreate_schema(TSqlParser.Create_schemaContext ctx) {

    }

    @Override
    public void enterCreate_schema_azure_sql_dw_and_pdw(TSqlParser.Create_schema_azure_sql_dw_and_pdwContext ctx) {

    }

    @Override
    public void exitCreate_schema_azure_sql_dw_and_pdw(TSqlParser.Create_schema_azure_sql_dw_and_pdwContext ctx) {

    }

    @Override
    public void enterAlter_schema_azure_sql_dw_and_pdw(TSqlParser.Alter_schema_azure_sql_dw_and_pdwContext ctx) {

    }

    @Override
    public void exitAlter_schema_azure_sql_dw_and_pdw(TSqlParser.Alter_schema_azure_sql_dw_and_pdwContext ctx) {

    }

    @Override
    public void enterCreate_search_property_list(TSqlParser.Create_search_property_listContext ctx) {

    }

    @Override
    public void exitCreate_search_property_list(TSqlParser.Create_search_property_listContext ctx) {

    }

    @Override
    public void enterCreate_security_policy(TSqlParser.Create_security_policyContext ctx) {

    }

    @Override
    public void exitCreate_security_policy(TSqlParser.Create_security_policyContext ctx) {

    }

    @Override
    public void enterAlter_sequence(TSqlParser.Alter_sequenceContext ctx) {

    }

    @Override
    public void exitAlter_sequence(TSqlParser.Alter_sequenceContext ctx) {

    }

    @Override
    public void enterCreate_sequence(TSqlParser.Create_sequenceContext ctx) {

    }

    @Override
    public void exitCreate_sequence(TSqlParser.Create_sequenceContext ctx) {

    }

    @Override
    public void enterAlter_server_audit(TSqlParser.Alter_server_auditContext ctx) {

    }

    @Override
    public void exitAlter_server_audit(TSqlParser.Alter_server_auditContext ctx) {

    }

    @Override
    public void enterCreate_server_audit(TSqlParser.Create_server_auditContext ctx) {

    }

    @Override
    public void exitCreate_server_audit(TSqlParser.Create_server_auditContext ctx) {

    }

    @Override
    public void enterAlter_server_audit_specification(TSqlParser.Alter_server_audit_specificationContext ctx) {

    }

    @Override
    public void exitAlter_server_audit_specification(TSqlParser.Alter_server_audit_specificationContext ctx) {

    }

    @Override
    public void enterCreate_server_audit_specification(TSqlParser.Create_server_audit_specificationContext ctx) {

    }

    @Override
    public void exitCreate_server_audit_specification(TSqlParser.Create_server_audit_specificationContext ctx) {

    }

    @Override
    public void enterAlter_server_configuration(TSqlParser.Alter_server_configurationContext ctx) {

    }

    @Override
    public void exitAlter_server_configuration(TSqlParser.Alter_server_configurationContext ctx) {

    }

    @Override
    public void enterAlter_server_role(TSqlParser.Alter_server_roleContext ctx) {

    }

    @Override
    public void exitAlter_server_role(TSqlParser.Alter_server_roleContext ctx) {

    }

    @Override
    public void enterCreate_server_role(TSqlParser.Create_server_roleContext ctx) {

    }

    @Override
    public void exitCreate_server_role(TSqlParser.Create_server_roleContext ctx) {

    }

    @Override
    public void enterAlter_server_role_pdw(TSqlParser.Alter_server_role_pdwContext ctx) {

    }

    @Override
    public void exitAlter_server_role_pdw(TSqlParser.Alter_server_role_pdwContext ctx) {

    }

    @Override
    public void enterAlter_service(TSqlParser.Alter_serviceContext ctx) {

    }

    @Override
    public void exitAlter_service(TSqlParser.Alter_serviceContext ctx) {

    }

    @Override
    public void enterCreate_service(TSqlParser.Create_serviceContext ctx) {

    }

    @Override
    public void exitCreate_service(TSqlParser.Create_serviceContext ctx) {

    }

    @Override
    public void enterAlter_service_master_key(TSqlParser.Alter_service_master_keyContext ctx) {

    }

    @Override
    public void exitAlter_service_master_key(TSqlParser.Alter_service_master_keyContext ctx) {

    }

    @Override
    public void enterAlter_symmetric_key(TSqlParser.Alter_symmetric_keyContext ctx) {

    }

    @Override
    public void exitAlter_symmetric_key(TSqlParser.Alter_symmetric_keyContext ctx) {

    }

    @Override
    public void enterCreate_symmetric_key(TSqlParser.Create_symmetric_keyContext ctx) {

    }

    @Override
    public void exitCreate_symmetric_key(TSqlParser.Create_symmetric_keyContext ctx) {

    }

    @Override
    public void enterCreate_synonym(TSqlParser.Create_synonymContext ctx) {

    }

    @Override
    public void exitCreate_synonym(TSqlParser.Create_synonymContext ctx) {

    }

    @Override
    public void enterAlter_user(TSqlParser.Alter_userContext ctx) {

    }

    @Override
    public void exitAlter_user(TSqlParser.Alter_userContext ctx) {

    }

    @Override
    public void enterCreate_user(TSqlParser.Create_userContext ctx) {

    }

    @Override
    public void exitCreate_user(TSqlParser.Create_userContext ctx) {

    }

    @Override
    public void enterCreate_user_azure_sql_dw(TSqlParser.Create_user_azure_sql_dwContext ctx) {

    }

    @Override
    public void exitCreate_user_azure_sql_dw(TSqlParser.Create_user_azure_sql_dwContext ctx) {

    }

    @Override
    public void enterAlter_user_azure_sql(TSqlParser.Alter_user_azure_sqlContext ctx) {

    }

    @Override
    public void exitAlter_user_azure_sql(TSqlParser.Alter_user_azure_sqlContext ctx) {

    }

    @Override
    public void enterAlter_workload_group(TSqlParser.Alter_workload_groupContext ctx) {

    }

    @Override
    public void exitAlter_workload_group(TSqlParser.Alter_workload_groupContext ctx) {

    }

    @Override
    public void enterCreate_workload_group(TSqlParser.Create_workload_groupContext ctx) {

    }

    @Override
    public void exitCreate_workload_group(TSqlParser.Create_workload_groupContext ctx) {

    }

    @Override
    public void enterCreate_xml_schema_collection(TSqlParser.Create_xml_schema_collectionContext ctx) {

    }

    @Override
    public void exitCreate_xml_schema_collection(TSqlParser.Create_xml_schema_collectionContext ctx) {

    }

    @Override
    public void enterCreate_queue(TSqlParser.Create_queueContext ctx) {

    }

    @Override
    public void exitCreate_queue(TSqlParser.Create_queueContext ctx) {

    }

    @Override
    public void enterQueue_settings(TSqlParser.Queue_settingsContext ctx) {

    }

    @Override
    public void exitQueue_settings(TSqlParser.Queue_settingsContext ctx) {

    }

    @Override
    public void enterAlter_queue(TSqlParser.Alter_queueContext ctx) {

    }

    @Override
    public void exitAlter_queue(TSqlParser.Alter_queueContext ctx) {

    }

    @Override
    public void enterQueue_action(TSqlParser.Queue_actionContext ctx) {

    }

    @Override
    public void exitQueue_action(TSqlParser.Queue_actionContext ctx) {

    }

    @Override
    public void enterQueue_rebuild_options(TSqlParser.Queue_rebuild_optionsContext ctx) {

    }

    @Override
    public void exitQueue_rebuild_options(TSqlParser.Queue_rebuild_optionsContext ctx) {

    }

    @Override
    public void enterCreate_contract(TSqlParser.Create_contractContext ctx) {

    }

    @Override
    public void exitCreate_contract(TSqlParser.Create_contractContext ctx) {

    }

    @Override
    public void enterConversation_statement(TSqlParser.Conversation_statementContext ctx) {

    }

    @Override
    public void exitConversation_statement(TSqlParser.Conversation_statementContext ctx) {

    }

    @Override
    public void enterMessage_statement(TSqlParser.Message_statementContext ctx) {

    }

    @Override
    public void exitMessage_statement(TSqlParser.Message_statementContext ctx) {

    }

    @Override
    public void enterMerge_statement(TSqlParser.Merge_statementContext ctx) {

    }

    @Override
    public void exitMerge_statement(TSqlParser.Merge_statementContext ctx) {

    }

    @Override
    public void enterMerge_matched(TSqlParser.Merge_matchedContext ctx) {

    }

    @Override
    public void exitMerge_matched(TSqlParser.Merge_matchedContext ctx) {

    }

    @Override
    public void enterMerge_not_matched(TSqlParser.Merge_not_matchedContext ctx) {

    }

    @Override
    public void exitMerge_not_matched(TSqlParser.Merge_not_matchedContext ctx) {

    }

    @Override
    public void enterDelete_statement(TSqlParser.Delete_statementContext ctx) {

    }

    @Override
    public void exitDelete_statement(TSqlParser.Delete_statementContext ctx) {

    }

    @Override
    public void enterDelete_statement_from(TSqlParser.Delete_statement_fromContext ctx) {

    }

    @Override
    public void exitDelete_statement_from(TSqlParser.Delete_statement_fromContext ctx) {

    }

    @Override
    public void enterInsert_statement(TSqlParser.Insert_statementContext ctx) {

    }

    @Override
    public void exitInsert_statement(TSqlParser.Insert_statementContext ctx) {

    }

    @Override
    public void enterInsert_statement_value(TSqlParser.Insert_statement_valueContext ctx) {

    }

    @Override
    public void exitInsert_statement_value(TSqlParser.Insert_statement_valueContext ctx) {

    }

    @Override
    public void enterReceive_statement(TSqlParser.Receive_statementContext ctx) {

    }

    @Override
    public void exitReceive_statement(TSqlParser.Receive_statementContext ctx) {

    }

    @Override
    public void enterSelect_statement(TSqlParser.Select_statementContext ctx) {

    }

    @Override
    public void exitSelect_statement(TSqlParser.Select_statementContext ctx) {

    }

    @Override
    public void enterTime(TSqlParser.TimeContext ctx) {

    }

    @Override
    public void exitTime(TSqlParser.TimeContext ctx) {

    }

    @Override
    public void enterUpdate_statement(TSqlParser.Update_statementContext ctx) {

    }

    @Override
    public void exitUpdate_statement(TSqlParser.Update_statementContext ctx) {

    }

    @Override
    public void enterOutput_clause(TSqlParser.Output_clauseContext ctx) {

    }

    @Override
    public void exitOutput_clause(TSqlParser.Output_clauseContext ctx) {

    }

    @Override
    public void enterOutput_dml_list_elem(TSqlParser.Output_dml_list_elemContext ctx) {

    }

    @Override
    public void exitOutput_dml_list_elem(TSqlParser.Output_dml_list_elemContext ctx) {

    }

    @Override
    public void enterOutput_column_name(TSqlParser.Output_column_nameContext ctx) {

    }

    @Override
    public void exitOutput_column_name(TSqlParser.Output_column_nameContext ctx) {

    }

    @Override
    public void enterCreate_database(TSqlParser.Create_databaseContext ctx) {

    }

    @Override
    public void exitCreate_database(TSqlParser.Create_databaseContext ctx) {

    }

    @Override
    public void enterCreate_index(TSqlParser.Create_indexContext ctx) {

    }

    @Override
    public void exitCreate_index(TSqlParser.Create_indexContext ctx) {

    }

    @Override
    public void enterCreate_or_alter_procedure(TSqlParser.Create_or_alter_procedureContext ctx) {

    }

    @Override
    public void exitCreate_or_alter_procedure(TSqlParser.Create_or_alter_procedureContext ctx) {

    }

    @Override
    public void enterCreate_or_alter_trigger(TSqlParser.Create_or_alter_triggerContext ctx) {

    }

    @Override
    public void exitCreate_or_alter_trigger(TSqlParser.Create_or_alter_triggerContext ctx) {

    }

    @Override
    public void enterCreate_or_alter_dml_trigger(TSqlParser.Create_or_alter_dml_triggerContext ctx) {

    }

    @Override
    public void exitCreate_or_alter_dml_trigger(TSqlParser.Create_or_alter_dml_triggerContext ctx) {

    }

    @Override
    public void enterDml_trigger_option(TSqlParser.Dml_trigger_optionContext ctx) {

    }

    @Override
    public void exitDml_trigger_option(TSqlParser.Dml_trigger_optionContext ctx) {

    }

    @Override
    public void enterDml_trigger_operation(TSqlParser.Dml_trigger_operationContext ctx) {

    }

    @Override
    public void exitDml_trigger_operation(TSqlParser.Dml_trigger_operationContext ctx) {

    }

    @Override
    public void enterCreate_or_alter_ddl_trigger(TSqlParser.Create_or_alter_ddl_triggerContext ctx) {

    }

    @Override
    public void exitCreate_or_alter_ddl_trigger(TSqlParser.Create_or_alter_ddl_triggerContext ctx) {

    }

    @Override
    public void enterDdl_trigger_operation(TSqlParser.Ddl_trigger_operationContext ctx) {

    }

    @Override
    public void exitDdl_trigger_operation(TSqlParser.Ddl_trigger_operationContext ctx) {

    }

    @Override
    public void enterCreate_or_alter_function(TSqlParser.Create_or_alter_functionContext ctx) {

    }

    @Override
    public void exitCreate_or_alter_function(TSqlParser.Create_or_alter_functionContext ctx) {

    }

    @Override
    public void enterFunc_body_returns_select(TSqlParser.Func_body_returns_selectContext ctx) {

    }

    @Override
    public void exitFunc_body_returns_select(TSqlParser.Func_body_returns_selectContext ctx) {

    }

    @Override
    public void enterFunc_body_returns_table(TSqlParser.Func_body_returns_tableContext ctx) {

    }

    @Override
    public void exitFunc_body_returns_table(TSqlParser.Func_body_returns_tableContext ctx) {

    }

    @Override
    public void enterFunc_body_returns_scalar(TSqlParser.Func_body_returns_scalarContext ctx) {

    }

    @Override
    public void exitFunc_body_returns_scalar(TSqlParser.Func_body_returns_scalarContext ctx) {

    }

    @Override
    public void enterProcedure_param(TSqlParser.Procedure_paramContext ctx) {

    }

    @Override
    public void exitProcedure_param(TSqlParser.Procedure_paramContext ctx) {

    }

    @Override
    public void enterProcedure_option(TSqlParser.Procedure_optionContext ctx) {

    }

    @Override
    public void exitProcedure_option(TSqlParser.Procedure_optionContext ctx) {

    }

    @Override
    public void enterFunction_option(TSqlParser.Function_optionContext ctx) {

    }

    @Override
    public void exitFunction_option(TSqlParser.Function_optionContext ctx) {

    }

    @Override
    public void enterCreate_statistics(TSqlParser.Create_statisticsContext ctx) {

    }

    @Override
    public void exitCreate_statistics(TSqlParser.Create_statisticsContext ctx) {

    }

    @Override
    public void enterUpdate_statistics(TSqlParser.Update_statisticsContext ctx) {

    }

    @Override
    public void exitUpdate_statistics(TSqlParser.Update_statisticsContext ctx) {

    }

    @Override
    public void enterCreate_table(TSqlParser.Create_tableContext ctx) {
        Entity entity = new Entity(stripBracketId(ctx.table_name().table.getText()));

        for (TSqlParser.Column_def_table_constraintContext c : ctx.column_def_table_constraints()
                .column_def_table_constraint()) {

            if (c.column_definition() != null) {
                TSqlParser.Column_definitionContext colContext = c.column_definition();
                Field field = getField(colContext.data_type());

                field.setName(stripBracketId(c.column_definition().id(0).SQUARE_BRACKET_ID().toString()));
                entity.getFields().put(field.getName(), field);
            }
        }

        dataModel.getEntities().put(entity.getName(), entity);
    }

    public DataModel getDataModel() {
        return dataModel;
    }

    private Field getField(TSqlParser.Data_typeContext typeContext) {
        if (typeContext.DOUBLE() != null) {
            return new FloatField();
        } else if (typeContext.INT() != null || typeContext.TINYINT() != null || typeContext
                .SMALLINT() != null || typeContext.BIGINT() != null) {
            return new IntegerField();
        } else {
            switch (stripBracketId(typeContext.id().SQUARE_BRACKET_ID().toString())) {
                case "int":
                    return new IntegerField();
                case "bigint":
                    return new BigIntegerField();
                case "nvarchar":
                    return new VarCharField();
                case "varchar":
                    return new VarCharField();
                case "decimal":
                    return new DecimalField();
                case "datetimeoffset":
                    return new DateTimeOffsetField();
                case "date":
                    return new DateField();
                case "bit":
                    return new BooleanField();
                case "datetime":
                    return new DateTimeField();
                case "datetime2":
                    return new DateTimeField();
                case "char":
                    return new CharField();
                default:
                    throw new RuntimeException(String.format("Missing case for fieldType %s",
                            typeContext.id().SQUARE_BRACKET_ID().toString()));
            }
        }
    }

    private String stripBracketId(String bracketId) {
        if (bracketId.charAt(0) =='[') {
            bracketId = bracketId.substring(1);
        }

        if (bracketId.charAt(bracketId.length() - 1) == ']') {
            bracketId = bracketId.substring(0, bracketId.length() - 1);
        }

        return bracketId;
    }

    @Override
    public void exitCreate_table(TSqlParser.Create_tableContext ctx) {

    }

    @Override
    public void enterTable_options(TSqlParser.Table_optionsContext ctx) {

    }

    @Override
    public void exitTable_options(TSqlParser.Table_optionsContext ctx) {

    }

    @Override
    public void enterCreate_view(TSqlParser.Create_viewContext ctx) {

    }

    @Override
    public void exitCreate_view(TSqlParser.Create_viewContext ctx) {

    }

    @Override
    public void enterView_attribute(TSqlParser.View_attributeContext ctx) {

    }

    @Override
    public void exitView_attribute(TSqlParser.View_attributeContext ctx) {

    }

    @Override
    public void enterAlter_table(TSqlParser.Alter_tableContext ctx) {

    }

    @Override
    public void exitAlter_table(TSqlParser.Alter_tableContext ctx) {

    }

    @Override
    public void enterAlter_database(TSqlParser.Alter_databaseContext ctx) {

    }

    @Override
    public void exitAlter_database(TSqlParser.Alter_databaseContext ctx) {

    }

    @Override
    public void enterDatabase_optionspec(TSqlParser.Database_optionspecContext ctx) {

    }

    @Override
    public void exitDatabase_optionspec(TSqlParser.Database_optionspecContext ctx) {

    }

    @Override
    public void enterAuto_option(TSqlParser.Auto_optionContext ctx) {

    }

    @Override
    public void exitAuto_option(TSqlParser.Auto_optionContext ctx) {

    }

    @Override
    public void enterChange_tracking_option(TSqlParser.Change_tracking_optionContext ctx) {

    }

    @Override
    public void exitChange_tracking_option(TSqlParser.Change_tracking_optionContext ctx) {

    }

    @Override
    public void enterChange_tracking_option_list(TSqlParser.Change_tracking_option_listContext ctx) {

    }

    @Override
    public void exitChange_tracking_option_list(TSqlParser.Change_tracking_option_listContext ctx) {

    }

    @Override
    public void enterContainment_option(TSqlParser.Containment_optionContext ctx) {

    }

    @Override
    public void exitContainment_option(TSqlParser.Containment_optionContext ctx) {

    }

    @Override
    public void enterCursor_option(TSqlParser.Cursor_optionContext ctx) {

    }

    @Override
    public void exitCursor_option(TSqlParser.Cursor_optionContext ctx) {

    }

    @Override
    public void enterAlter_endpoint(TSqlParser.Alter_endpointContext ctx) {

    }

    @Override
    public void exitAlter_endpoint(TSqlParser.Alter_endpointContext ctx) {

    }

    @Override
    public void enterDatabase_mirroring_option(TSqlParser.Database_mirroring_optionContext ctx) {

    }

    @Override
    public void exitDatabase_mirroring_option(TSqlParser.Database_mirroring_optionContext ctx) {

    }

    @Override
    public void enterMirroring_set_option(TSqlParser.Mirroring_set_optionContext ctx) {

    }

    @Override
    public void exitMirroring_set_option(TSqlParser.Mirroring_set_optionContext ctx) {

    }

    @Override
    public void enterMirroring_partner(TSqlParser.Mirroring_partnerContext ctx) {

    }

    @Override
    public void exitMirroring_partner(TSqlParser.Mirroring_partnerContext ctx) {

    }

    @Override
    public void enterMirroring_witness(TSqlParser.Mirroring_witnessContext ctx) {

    }

    @Override
    public void exitMirroring_witness(TSqlParser.Mirroring_witnessContext ctx) {

    }

    @Override
    public void enterWitness_partner_equal(TSqlParser.Witness_partner_equalContext ctx) {

    }

    @Override
    public void exitWitness_partner_equal(TSqlParser.Witness_partner_equalContext ctx) {

    }

    @Override
    public void enterPartner_option(TSqlParser.Partner_optionContext ctx) {

    }

    @Override
    public void exitPartner_option(TSqlParser.Partner_optionContext ctx) {

    }

    @Override
    public void enterWitness_option(TSqlParser.Witness_optionContext ctx) {

    }

    @Override
    public void exitWitness_option(TSqlParser.Witness_optionContext ctx) {

    }

    @Override
    public void enterWitness_server(TSqlParser.Witness_serverContext ctx) {

    }

    @Override
    public void exitWitness_server(TSqlParser.Witness_serverContext ctx) {

    }

    @Override
    public void enterPartner_server(TSqlParser.Partner_serverContext ctx) {

    }

    @Override
    public void exitPartner_server(TSqlParser.Partner_serverContext ctx) {

    }

    @Override
    public void enterMirroring_host_port_seperator(TSqlParser.Mirroring_host_port_seperatorContext ctx) {

    }

    @Override
    public void exitMirroring_host_port_seperator(TSqlParser.Mirroring_host_port_seperatorContext ctx) {

    }

    @Override
    public void enterPartner_server_tcp_prefix(TSqlParser.Partner_server_tcp_prefixContext ctx) {

    }

    @Override
    public void exitPartner_server_tcp_prefix(TSqlParser.Partner_server_tcp_prefixContext ctx) {

    }

    @Override
    public void enterPort_number(TSqlParser.Port_numberContext ctx) {

    }

    @Override
    public void exitPort_number(TSqlParser.Port_numberContext ctx) {

    }

    @Override
    public void enterHost(TSqlParser.HostContext ctx) {

    }

    @Override
    public void exitHost(TSqlParser.HostContext ctx) {

    }

    @Override
    public void enterDate_correlation_optimization_option(TSqlParser.Date_correlation_optimization_optionContext ctx) {

    }

    @Override
    public void exitDate_correlation_optimization_option(TSqlParser.Date_correlation_optimization_optionContext ctx) {

    }

    @Override
    public void enterDb_encryption_option(TSqlParser.Db_encryption_optionContext ctx) {

    }

    @Override
    public void exitDb_encryption_option(TSqlParser.Db_encryption_optionContext ctx) {

    }

    @Override
    public void enterDb_state_option(TSqlParser.Db_state_optionContext ctx) {

    }

    @Override
    public void exitDb_state_option(TSqlParser.Db_state_optionContext ctx) {

    }

    @Override
    public void enterDb_update_option(TSqlParser.Db_update_optionContext ctx) {

    }

    @Override
    public void exitDb_update_option(TSqlParser.Db_update_optionContext ctx) {

    }

    @Override
    public void enterDb_user_access_option(TSqlParser.Db_user_access_optionContext ctx) {

    }

    @Override
    public void exitDb_user_access_option(TSqlParser.Db_user_access_optionContext ctx) {

    }

    @Override
    public void enterDelayed_durability_option(TSqlParser.Delayed_durability_optionContext ctx) {

    }

    @Override
    public void exitDelayed_durability_option(TSqlParser.Delayed_durability_optionContext ctx) {

    }

    @Override
    public void enterExternal_access_option(TSqlParser.External_access_optionContext ctx) {

    }

    @Override
    public void exitExternal_access_option(TSqlParser.External_access_optionContext ctx) {

    }

    @Override
    public void enterHadr_options(TSqlParser.Hadr_optionsContext ctx) {

    }

    @Override
    public void exitHadr_options(TSqlParser.Hadr_optionsContext ctx) {

    }

    @Override
    public void enterMixed_page_allocation_option(TSqlParser.Mixed_page_allocation_optionContext ctx) {

    }

    @Override
    public void exitMixed_page_allocation_option(TSqlParser.Mixed_page_allocation_optionContext ctx) {

    }

    @Override
    public void enterParameterization_option(TSqlParser.Parameterization_optionContext ctx) {

    }

    @Override
    public void exitParameterization_option(TSqlParser.Parameterization_optionContext ctx) {

    }

    @Override
    public void enterRecovery_option(TSqlParser.Recovery_optionContext ctx) {

    }

    @Override
    public void exitRecovery_option(TSqlParser.Recovery_optionContext ctx) {

    }

    @Override
    public void enterService_broker_option(TSqlParser.Service_broker_optionContext ctx) {

    }

    @Override
    public void exitService_broker_option(TSqlParser.Service_broker_optionContext ctx) {

    }

    @Override
    public void enterSnapshot_option(TSqlParser.Snapshot_optionContext ctx) {

    }

    @Override
    public void exitSnapshot_option(TSqlParser.Snapshot_optionContext ctx) {

    }

    @Override
    public void enterSql_option(TSqlParser.Sql_optionContext ctx) {

    }

    @Override
    public void exitSql_option(TSqlParser.Sql_optionContext ctx) {

    }

    @Override
    public void enterTarget_recovery_time_option(TSqlParser.Target_recovery_time_optionContext ctx) {

    }

    @Override
    public void exitTarget_recovery_time_option(TSqlParser.Target_recovery_time_optionContext ctx) {

    }

    @Override
    public void enterTermination(TSqlParser.TerminationContext ctx) {

    }

    @Override
    public void exitTermination(TSqlParser.TerminationContext ctx) {

    }

    @Override
    public void enterDrop_index(TSqlParser.Drop_indexContext ctx) {

    }

    @Override
    public void exitDrop_index(TSqlParser.Drop_indexContext ctx) {

    }

    @Override
    public void enterDrop_relational_or_xml_or_spatial_index(TSqlParser.Drop_relational_or_xml_or_spatial_indexContext ctx) {

    }

    @Override
    public void exitDrop_relational_or_xml_or_spatial_index(TSqlParser.Drop_relational_or_xml_or_spatial_indexContext ctx) {

    }

    @Override
    public void enterDrop_backward_compatible_index(TSqlParser.Drop_backward_compatible_indexContext ctx) {

    }

    @Override
    public void exitDrop_backward_compatible_index(TSqlParser.Drop_backward_compatible_indexContext ctx) {

    }

    @Override
    public void enterDrop_procedure(TSqlParser.Drop_procedureContext ctx) {

    }

    @Override
    public void exitDrop_procedure(TSqlParser.Drop_procedureContext ctx) {

    }

    @Override
    public void enterDrop_trigger(TSqlParser.Drop_triggerContext ctx) {

    }

    @Override
    public void exitDrop_trigger(TSqlParser.Drop_triggerContext ctx) {

    }

    @Override
    public void enterDrop_dml_trigger(TSqlParser.Drop_dml_triggerContext ctx) {

    }

    @Override
    public void exitDrop_dml_trigger(TSqlParser.Drop_dml_triggerContext ctx) {

    }

    @Override
    public void enterDrop_ddl_trigger(TSqlParser.Drop_ddl_triggerContext ctx) {

    }

    @Override
    public void exitDrop_ddl_trigger(TSqlParser.Drop_ddl_triggerContext ctx) {

    }

    @Override
    public void enterDrop_function(TSqlParser.Drop_functionContext ctx) {

    }

    @Override
    public void exitDrop_function(TSqlParser.Drop_functionContext ctx) {

    }

    @Override
    public void enterDrop_statistics(TSqlParser.Drop_statisticsContext ctx) {

    }

    @Override
    public void exitDrop_statistics(TSqlParser.Drop_statisticsContext ctx) {

    }

    @Override
    public void enterDrop_table(TSqlParser.Drop_tableContext ctx) {

    }

    @Override
    public void exitDrop_table(TSqlParser.Drop_tableContext ctx) {

    }

    @Override
    public void enterDrop_view(TSqlParser.Drop_viewContext ctx) {

    }

    @Override
    public void exitDrop_view(TSqlParser.Drop_viewContext ctx) {

    }

    @Override
    public void enterCreate_type(TSqlParser.Create_typeContext ctx) {

    }

    @Override
    public void exitCreate_type(TSqlParser.Create_typeContext ctx) {

    }

    @Override
    public void enterDrop_type(TSqlParser.Drop_typeContext ctx) {

    }

    @Override
    public void exitDrop_type(TSqlParser.Drop_typeContext ctx) {

    }

    @Override
    public void enterRowset_function_limited(TSqlParser.Rowset_function_limitedContext ctx) {

    }

    @Override
    public void exitRowset_function_limited(TSqlParser.Rowset_function_limitedContext ctx) {

    }

    @Override
    public void enterOpenquery(TSqlParser.OpenqueryContext ctx) {

    }

    @Override
    public void exitOpenquery(TSqlParser.OpenqueryContext ctx) {

    }

    @Override
    public void enterOpendatasource(TSqlParser.OpendatasourceContext ctx) {

    }

    @Override
    public void exitOpendatasource(TSqlParser.OpendatasourceContext ctx) {

    }

    @Override
    public void enterDeclare_statement(TSqlParser.Declare_statementContext ctx) {

    }

    @Override
    public void exitDeclare_statement(TSqlParser.Declare_statementContext ctx) {

    }

    @Override
    public void enterCursor_statement(TSqlParser.Cursor_statementContext ctx) {

    }

    @Override
    public void exitCursor_statement(TSqlParser.Cursor_statementContext ctx) {

    }

    @Override
    public void enterBackup_database(TSqlParser.Backup_databaseContext ctx) {

    }

    @Override
    public void exitBackup_database(TSqlParser.Backup_databaseContext ctx) {

    }

    @Override
    public void enterBackup_log(TSqlParser.Backup_logContext ctx) {

    }

    @Override
    public void exitBackup_log(TSqlParser.Backup_logContext ctx) {

    }

    @Override
    public void enterBackup_certificate(TSqlParser.Backup_certificateContext ctx) {

    }

    @Override
    public void exitBackup_certificate(TSqlParser.Backup_certificateContext ctx) {

    }

    @Override
    public void enterBackup_master_key(TSqlParser.Backup_master_keyContext ctx) {

    }

    @Override
    public void exitBackup_master_key(TSqlParser.Backup_master_keyContext ctx) {

    }

    @Override
    public void enterBackup_service_master_key(TSqlParser.Backup_service_master_keyContext ctx) {

    }

    @Override
    public void exitBackup_service_master_key(TSqlParser.Backup_service_master_keyContext ctx) {

    }

    @Override
    public void enterExecute_statement(TSqlParser.Execute_statementContext ctx) {

    }

    @Override
    public void exitExecute_statement(TSqlParser.Execute_statementContext ctx) {

    }

    @Override
    public void enterExecute_body(TSqlParser.Execute_bodyContext ctx) {

    }

    @Override
    public void exitExecute_body(TSqlParser.Execute_bodyContext ctx) {

    }

    @Override
    public void enterExecute_statement_arg(TSqlParser.Execute_statement_argContext ctx) {

    }

    @Override
    public void exitExecute_statement_arg(TSqlParser.Execute_statement_argContext ctx) {

    }

    @Override
    public void enterExecute_var_string(TSqlParser.Execute_var_stringContext ctx) {

    }

    @Override
    public void exitExecute_var_string(TSqlParser.Execute_var_stringContext ctx) {

    }

    @Override
    public void enterSecurity_statement(TSqlParser.Security_statementContext ctx) {

    }

    @Override
    public void exitSecurity_statement(TSqlParser.Security_statementContext ctx) {

    }

    @Override
    public void enterCreate_certificate(TSqlParser.Create_certificateContext ctx) {

    }

    @Override
    public void exitCreate_certificate(TSqlParser.Create_certificateContext ctx) {

    }

    @Override
    public void enterExisting_keys(TSqlParser.Existing_keysContext ctx) {

    }

    @Override
    public void exitExisting_keys(TSqlParser.Existing_keysContext ctx) {

    }

    @Override
    public void enterPrivate_key_options(TSqlParser.Private_key_optionsContext ctx) {

    }

    @Override
    public void exitPrivate_key_options(TSqlParser.Private_key_optionsContext ctx) {

    }

    @Override
    public void enterGenerate_new_keys(TSqlParser.Generate_new_keysContext ctx) {

    }

    @Override
    public void exitGenerate_new_keys(TSqlParser.Generate_new_keysContext ctx) {

    }

    @Override
    public void enterDate_options(TSqlParser.Date_optionsContext ctx) {

    }

    @Override
    public void exitDate_options(TSqlParser.Date_optionsContext ctx) {

    }

    @Override
    public void enterOpen_key(TSqlParser.Open_keyContext ctx) {

    }

    @Override
    public void exitOpen_key(TSqlParser.Open_keyContext ctx) {

    }

    @Override
    public void enterClose_key(TSqlParser.Close_keyContext ctx) {

    }

    @Override
    public void exitClose_key(TSqlParser.Close_keyContext ctx) {

    }

    @Override
    public void enterCreate_key(TSqlParser.Create_keyContext ctx) {

    }

    @Override
    public void exitCreate_key(TSqlParser.Create_keyContext ctx) {

    }

    @Override
    public void enterKey_options(TSqlParser.Key_optionsContext ctx) {

    }

    @Override
    public void exitKey_options(TSqlParser.Key_optionsContext ctx) {

    }

    @Override
    public void enterAlgorithm(TSqlParser.AlgorithmContext ctx) {

    }

    @Override
    public void exitAlgorithm(TSqlParser.AlgorithmContext ctx) {

    }

    @Override
    public void enterEncryption_mechanism(TSqlParser.Encryption_mechanismContext ctx) {

    }

    @Override
    public void exitEncryption_mechanism(TSqlParser.Encryption_mechanismContext ctx) {

    }

    @Override
    public void enterDecryption_mechanism(TSqlParser.Decryption_mechanismContext ctx) {

    }

    @Override
    public void exitDecryption_mechanism(TSqlParser.Decryption_mechanismContext ctx) {

    }

    @Override
    public void enterGrant_permission(TSqlParser.Grant_permissionContext ctx) {

    }

    @Override
    public void exitGrant_permission(TSqlParser.Grant_permissionContext ctx) {

    }

    @Override
    public void enterSet_statement(TSqlParser.Set_statementContext ctx) {

    }

    @Override
    public void exitSet_statement(TSqlParser.Set_statementContext ctx) {

    }

    @Override
    public void enterTransaction_statement(TSqlParser.Transaction_statementContext ctx) {

    }

    @Override
    public void exitTransaction_statement(TSqlParser.Transaction_statementContext ctx) {

    }

    @Override
    public void enterGo_statement(TSqlParser.Go_statementContext ctx) {

    }

    @Override
    public void exitGo_statement(TSqlParser.Go_statementContext ctx) {

    }

    @Override
    public void enterUse_statement(TSqlParser.Use_statementContext ctx) {

    }

    @Override
    public void exitUse_statement(TSqlParser.Use_statementContext ctx) {

    }

    @Override
    public void enterSetuser_statement(TSqlParser.Setuser_statementContext ctx) {

    }

    @Override
    public void exitSetuser_statement(TSqlParser.Setuser_statementContext ctx) {

    }

    @Override
    public void enterDbcc_clause(TSqlParser.Dbcc_clauseContext ctx) {

    }

    @Override
    public void exitDbcc_clause(TSqlParser.Dbcc_clauseContext ctx) {

    }

    @Override
    public void enterDbcc_options(TSqlParser.Dbcc_optionsContext ctx) {

    }

    @Override
    public void exitDbcc_options(TSqlParser.Dbcc_optionsContext ctx) {

    }

    @Override
    public void enterExecute_clause(TSqlParser.Execute_clauseContext ctx) {

    }

    @Override
    public void exitExecute_clause(TSqlParser.Execute_clauseContext ctx) {

    }

    @Override
    public void enterDeclare_local(TSqlParser.Declare_localContext ctx) {

    }

    @Override
    public void exitDeclare_local(TSqlParser.Declare_localContext ctx) {

    }

    @Override
    public void enterTable_type_definition(TSqlParser.Table_type_definitionContext ctx) {

    }

    @Override
    public void exitTable_type_definition(TSqlParser.Table_type_definitionContext ctx) {

    }

    @Override
    public void enterXml_type_definition(TSqlParser.Xml_type_definitionContext ctx) {

    }

    @Override
    public void exitXml_type_definition(TSqlParser.Xml_type_definitionContext ctx) {

    }

    @Override
    public void enterXml_schema_collection(TSqlParser.Xml_schema_collectionContext ctx) {

    }

    @Override
    public void exitXml_schema_collection(TSqlParser.Xml_schema_collectionContext ctx) {

    }

    @Override
    public void enterColumn_def_table_constraints(TSqlParser.Column_def_table_constraintsContext ctx) {

    }

    @Override
    public void exitColumn_def_table_constraints(TSqlParser.Column_def_table_constraintsContext ctx) {

    }

    @Override
    public void enterColumn_def_table_constraint(TSqlParser.Column_def_table_constraintContext ctx) {

    }

    @Override
    public void exitColumn_def_table_constraint(TSqlParser.Column_def_table_constraintContext ctx) {

    }

    @Override
    public void enterColumn_definition(TSqlParser.Column_definitionContext ctx) {

    }

    @Override
    public void exitColumn_definition(TSqlParser.Column_definitionContext ctx) {

    }

    @Override
    public void enterMaterialized_column_definition(TSqlParser.Materialized_column_definitionContext ctx) {

    }

    @Override
    public void exitMaterialized_column_definition(TSqlParser.Materialized_column_definitionContext ctx) {

    }

    @Override
    public void enterColumn_constraint(TSqlParser.Column_constraintContext ctx) {

    }

    @Override
    public void exitColumn_constraint(TSqlParser.Column_constraintContext ctx) {

    }

    @Override
    public void enterTable_constraint(TSqlParser.Table_constraintContext ctx) {

    }

    @Override
    public void exitTable_constraint(TSqlParser.Table_constraintContext ctx) {

    }

    @Override
    public void enterOn_delete(TSqlParser.On_deleteContext ctx) {

    }

    @Override
    public void exitOn_delete(TSqlParser.On_deleteContext ctx) {

    }

    @Override
    public void enterOn_update(TSqlParser.On_updateContext ctx) {

    }

    @Override
    public void exitOn_update(TSqlParser.On_updateContext ctx) {

    }

    @Override
    public void enterIndex_options(TSqlParser.Index_optionsContext ctx) {

    }

    @Override
    public void exitIndex_options(TSqlParser.Index_optionsContext ctx) {

    }

    @Override
    public void enterIndex_option(TSqlParser.Index_optionContext ctx) {

    }

    @Override
    public void exitIndex_option(TSqlParser.Index_optionContext ctx) {

    }

    @Override
    public void enterDeclare_cursor(TSqlParser.Declare_cursorContext ctx) {

    }

    @Override
    public void exitDeclare_cursor(TSqlParser.Declare_cursorContext ctx) {

    }

    @Override
    public void enterDeclare_set_cursor_common(TSqlParser.Declare_set_cursor_commonContext ctx) {

    }

    @Override
    public void exitDeclare_set_cursor_common(TSqlParser.Declare_set_cursor_commonContext ctx) {

    }

    @Override
    public void enterDeclare_set_cursor_common_partial(TSqlParser.Declare_set_cursor_common_partialContext ctx) {

    }

    @Override
    public void exitDeclare_set_cursor_common_partial(TSqlParser.Declare_set_cursor_common_partialContext ctx) {

    }

    @Override
    public void enterFetch_cursor(TSqlParser.Fetch_cursorContext ctx) {

    }

    @Override
    public void exitFetch_cursor(TSqlParser.Fetch_cursorContext ctx) {

    }

    @Override
    public void enterSet_special(TSqlParser.Set_specialContext ctx) {

    }

    @Override
    public void exitSet_special(TSqlParser.Set_specialContext ctx) {

    }

    @Override
    public void enterConstant_LOCAL_ID(TSqlParser.Constant_LOCAL_IDContext ctx) {

    }

    @Override
    public void exitConstant_LOCAL_ID(TSqlParser.Constant_LOCAL_IDContext ctx) {

    }

    @Override
    public void enterExpression(TSqlParser.ExpressionContext ctx) {

    }

    @Override
    public void exitExpression(TSqlParser.ExpressionContext ctx) {

    }

    @Override
    public void enterPrimitive_expression(TSqlParser.Primitive_expressionContext ctx) {

    }

    @Override
    public void exitPrimitive_expression(TSqlParser.Primitive_expressionContext ctx) {

    }

    @Override
    public void enterCase_expression(TSqlParser.Case_expressionContext ctx) {

    }

    @Override
    public void exitCase_expression(TSqlParser.Case_expressionContext ctx) {

    }

    @Override
    public void enterUnary_operator_expression(TSqlParser.Unary_operator_expressionContext ctx) {

    }

    @Override
    public void exitUnary_operator_expression(TSqlParser.Unary_operator_expressionContext ctx) {

    }

    @Override
    public void enterBracket_expression(TSqlParser.Bracket_expressionContext ctx) {

    }

    @Override
    public void exitBracket_expression(TSqlParser.Bracket_expressionContext ctx) {

    }

    @Override
    public void enterConstant_expression(TSqlParser.Constant_expressionContext ctx) {

    }

    @Override
    public void exitConstant_expression(TSqlParser.Constant_expressionContext ctx) {

    }

    @Override
    public void enterSubquery(TSqlParser.SubqueryContext ctx) {

    }

    @Override
    public void exitSubquery(TSqlParser.SubqueryContext ctx) {

    }

    @Override
    public void enterWith_expression(TSqlParser.With_expressionContext ctx) {

    }

    @Override
    public void exitWith_expression(TSqlParser.With_expressionContext ctx) {

    }

    @Override
    public void enterCommon_table_expression(TSqlParser.Common_table_expressionContext ctx) {

    }

    @Override
    public void exitCommon_table_expression(TSqlParser.Common_table_expressionContext ctx) {

    }

    @Override
    public void enterUpdate_elem(TSqlParser.Update_elemContext ctx) {

    }

    @Override
    public void exitUpdate_elem(TSqlParser.Update_elemContext ctx) {

    }

    @Override
    public void enterSearch_condition_list(TSqlParser.Search_condition_listContext ctx) {

    }

    @Override
    public void exitSearch_condition_list(TSqlParser.Search_condition_listContext ctx) {

    }

    @Override
    public void enterSearch_condition(TSqlParser.Search_conditionContext ctx) {

    }

    @Override
    public void exitSearch_condition(TSqlParser.Search_conditionContext ctx) {

    }

    @Override
    public void enterSearch_condition_and(TSqlParser.Search_condition_andContext ctx) {

    }

    @Override
    public void exitSearch_condition_and(TSqlParser.Search_condition_andContext ctx) {

    }

    @Override
    public void enterSearch_condition_not(TSqlParser.Search_condition_notContext ctx) {

    }

    @Override
    public void exitSearch_condition_not(TSqlParser.Search_condition_notContext ctx) {

    }

    @Override
    public void enterPredicate(TSqlParser.PredicateContext ctx) {

    }

    @Override
    public void exitPredicate(TSqlParser.PredicateContext ctx) {

    }

    @Override
    public void enterQuery_expression(TSqlParser.Query_expressionContext ctx) {

    }

    @Override
    public void exitQuery_expression(TSqlParser.Query_expressionContext ctx) {

    }

    @Override
    public void enterSql_union(TSqlParser.Sql_unionContext ctx) {

    }

    @Override
    public void exitSql_union(TSqlParser.Sql_unionContext ctx) {

    }

    @Override
    public void enterQuery_specification(TSqlParser.Query_specificationContext ctx) {

    }

    @Override
    public void exitQuery_specification(TSqlParser.Query_specificationContext ctx) {

    }

    @Override
    public void enterTop_clause(TSqlParser.Top_clauseContext ctx) {

    }

    @Override
    public void exitTop_clause(TSqlParser.Top_clauseContext ctx) {

    }

    @Override
    public void enterTop_percent(TSqlParser.Top_percentContext ctx) {

    }

    @Override
    public void exitTop_percent(TSqlParser.Top_percentContext ctx) {

    }

    @Override
    public void enterTop_count(TSqlParser.Top_countContext ctx) {

    }

    @Override
    public void exitTop_count(TSqlParser.Top_countContext ctx) {

    }

    @Override
    public void enterOrder_by_clause(TSqlParser.Order_by_clauseContext ctx) {

    }

    @Override
    public void exitOrder_by_clause(TSqlParser.Order_by_clauseContext ctx) {

    }

    @Override
    public void enterFor_clause(TSqlParser.For_clauseContext ctx) {

    }

    @Override
    public void exitFor_clause(TSqlParser.For_clauseContext ctx) {

    }

    @Override
    public void enterXml_common_directives(TSqlParser.Xml_common_directivesContext ctx) {

    }

    @Override
    public void exitXml_common_directives(TSqlParser.Xml_common_directivesContext ctx) {

    }

    @Override
    public void enterOrder_by_expression(TSqlParser.Order_by_expressionContext ctx) {

    }

    @Override
    public void exitOrder_by_expression(TSqlParser.Order_by_expressionContext ctx) {

    }

    @Override
    public void enterGroup_by_item(TSqlParser.Group_by_itemContext ctx) {

    }

    @Override
    public void exitGroup_by_item(TSqlParser.Group_by_itemContext ctx) {

    }

    @Override
    public void enterOption_clause(TSqlParser.Option_clauseContext ctx) {

    }

    @Override
    public void exitOption_clause(TSqlParser.Option_clauseContext ctx) {

    }

    @Override
    public void enterOption(TSqlParser.OptionContext ctx) {

    }

    @Override
    public void exitOption(TSqlParser.OptionContext ctx) {

    }

    @Override
    public void enterOptimize_for_arg(TSqlParser.Optimize_for_argContext ctx) {

    }

    @Override
    public void exitOptimize_for_arg(TSqlParser.Optimize_for_argContext ctx) {

    }

    @Override
    public void enterSelect_list(TSqlParser.Select_listContext ctx) {

    }

    @Override
    public void exitSelect_list(TSqlParser.Select_listContext ctx) {

    }

    @Override
    public void enterUdt_method_arguments(TSqlParser.Udt_method_argumentsContext ctx) {

    }

    @Override
    public void exitUdt_method_arguments(TSqlParser.Udt_method_argumentsContext ctx) {

    }

    @Override
    public void enterAsterisk(TSqlParser.AsteriskContext ctx) {

    }

    @Override
    public void exitAsterisk(TSqlParser.AsteriskContext ctx) {

    }

    @Override
    public void enterColumn_elem(TSqlParser.Column_elemContext ctx) {

    }

    @Override
    public void exitColumn_elem(TSqlParser.Column_elemContext ctx) {

    }

    @Override
    public void enterUdt_elem(TSqlParser.Udt_elemContext ctx) {

    }

    @Override
    public void exitUdt_elem(TSqlParser.Udt_elemContext ctx) {

    }

    @Override
    public void enterExpression_elem(TSqlParser.Expression_elemContext ctx) {

    }

    @Override
    public void exitExpression_elem(TSqlParser.Expression_elemContext ctx) {

    }

    @Override
    public void enterSelect_list_elem(TSqlParser.Select_list_elemContext ctx) {

    }

    @Override
    public void exitSelect_list_elem(TSqlParser.Select_list_elemContext ctx) {

    }

    @Override
    public void enterTable_sources(TSqlParser.Table_sourcesContext ctx) {

    }

    @Override
    public void exitTable_sources(TSqlParser.Table_sourcesContext ctx) {

    }

    @Override
    public void enterTable_source(TSqlParser.Table_sourceContext ctx) {

    }

    @Override
    public void exitTable_source(TSqlParser.Table_sourceContext ctx) {

    }

    @Override
    public void enterTable_source_item_joined(TSqlParser.Table_source_item_joinedContext ctx) {

    }

    @Override
    public void exitTable_source_item_joined(TSqlParser.Table_source_item_joinedContext ctx) {

    }

    @Override
    public void enterTable_source_item(TSqlParser.Table_source_itemContext ctx) {

    }

    @Override
    public void exitTable_source_item(TSqlParser.Table_source_itemContext ctx) {

    }

    @Override
    public void enterOpen_xml(TSqlParser.Open_xmlContext ctx) {

    }

    @Override
    public void exitOpen_xml(TSqlParser.Open_xmlContext ctx) {

    }

    @Override
    public void enterSchema_declaration(TSqlParser.Schema_declarationContext ctx) {

    }

    @Override
    public void exitSchema_declaration(TSqlParser.Schema_declarationContext ctx) {

    }

    @Override
    public void enterColumn_declaration(TSqlParser.Column_declarationContext ctx) {

    }

    @Override
    public void exitColumn_declaration(TSqlParser.Column_declarationContext ctx) {

    }

    @Override
    public void enterChange_table(TSqlParser.Change_tableContext ctx) {

    }

    @Override
    public void exitChange_table(TSqlParser.Change_tableContext ctx) {

    }

    @Override
    public void enterJoin_part(TSqlParser.Join_partContext ctx) {

    }

    @Override
    public void exitJoin_part(TSqlParser.Join_partContext ctx) {

    }

    @Override
    public void enterPivot_clause(TSqlParser.Pivot_clauseContext ctx) {

    }

    @Override
    public void exitPivot_clause(TSqlParser.Pivot_clauseContext ctx) {

    }

    @Override
    public void enterUnpivot_clause(TSqlParser.Unpivot_clauseContext ctx) {

    }

    @Override
    public void exitUnpivot_clause(TSqlParser.Unpivot_clauseContext ctx) {

    }

    @Override
    public void enterFull_column_name_list(TSqlParser.Full_column_name_listContext ctx) {

    }

    @Override
    public void exitFull_column_name_list(TSqlParser.Full_column_name_listContext ctx) {

    }

    @Override
    public void enterTable_name_with_hint(TSqlParser.Table_name_with_hintContext ctx) {

    }

    @Override
    public void exitTable_name_with_hint(TSqlParser.Table_name_with_hintContext ctx) {

    }

    @Override
    public void enterRowset_function(TSqlParser.Rowset_functionContext ctx) {

    }

    @Override
    public void exitRowset_function(TSqlParser.Rowset_functionContext ctx) {

    }

    @Override
    public void enterBulk_option(TSqlParser.Bulk_optionContext ctx) {

    }

    @Override
    public void exitBulk_option(TSqlParser.Bulk_optionContext ctx) {

    }

    @Override
    public void enterDerived_table(TSqlParser.Derived_tableContext ctx) {

    }

    @Override
    public void exitDerived_table(TSqlParser.Derived_tableContext ctx) {

    }

    @Override
    public void enterRANKING_WINDOWED_FUNC(TSqlParser.RANKING_WINDOWED_FUNCContext ctx) {

    }

    @Override
    public void exitRANKING_WINDOWED_FUNC(TSqlParser.RANKING_WINDOWED_FUNCContext ctx) {

    }

    @Override
    public void enterAGGREGATE_WINDOWED_FUNC(TSqlParser.AGGREGATE_WINDOWED_FUNCContext ctx) {

    }

    @Override
    public void exitAGGREGATE_WINDOWED_FUNC(TSqlParser.AGGREGATE_WINDOWED_FUNCContext ctx) {

    }

    @Override
    public void enterANALYTIC_WINDOWED_FUNC(TSqlParser.ANALYTIC_WINDOWED_FUNCContext ctx) {

    }

    @Override
    public void exitANALYTIC_WINDOWED_FUNC(TSqlParser.ANALYTIC_WINDOWED_FUNCContext ctx) {

    }

    @Override
    public void enterSCALAR_FUNCTION(TSqlParser.SCALAR_FUNCTIONContext ctx) {

    }

    @Override
    public void exitSCALAR_FUNCTION(TSqlParser.SCALAR_FUNCTIONContext ctx) {

    }

    @Override
    public void enterBINARY_CHECKSUM(TSqlParser.BINARY_CHECKSUMContext ctx) {

    }

    @Override
    public void exitBINARY_CHECKSUM(TSqlParser.BINARY_CHECKSUMContext ctx) {

    }

    @Override
    public void enterCAST(TSqlParser.CASTContext ctx) {

    }

    @Override
    public void exitCAST(TSqlParser.CASTContext ctx) {

    }

    @Override
    public void enterCONVERT(TSqlParser.CONVERTContext ctx) {

    }

    @Override
    public void exitCONVERT(TSqlParser.CONVERTContext ctx) {

    }

    @Override
    public void enterCHECKSUM(TSqlParser.CHECKSUMContext ctx) {

    }

    @Override
    public void exitCHECKSUM(TSqlParser.CHECKSUMContext ctx) {

    }

    @Override
    public void enterCOALESCE(TSqlParser.COALESCEContext ctx) {

    }

    @Override
    public void exitCOALESCE(TSqlParser.COALESCEContext ctx) {

    }

    @Override
    public void enterCURRENT_TIMESTAMP(TSqlParser.CURRENT_TIMESTAMPContext ctx) {

    }

    @Override
    public void exitCURRENT_TIMESTAMP(TSqlParser.CURRENT_TIMESTAMPContext ctx) {

    }

    @Override
    public void enterCURRENT_USER(TSqlParser.CURRENT_USERContext ctx) {

    }

    @Override
    public void exitCURRENT_USER(TSqlParser.CURRENT_USERContext ctx) {

    }

    @Override
    public void enterDATEADD(TSqlParser.DATEADDContext ctx) {

    }

    @Override
    public void exitDATEADD(TSqlParser.DATEADDContext ctx) {

    }

    @Override
    public void enterDATEDIFF(TSqlParser.DATEDIFFContext ctx) {

    }

    @Override
    public void exitDATEDIFF(TSqlParser.DATEDIFFContext ctx) {

    }

    @Override
    public void enterDATENAME(TSqlParser.DATENAMEContext ctx) {

    }

    @Override
    public void exitDATENAME(TSqlParser.DATENAMEContext ctx) {

    }

    @Override
    public void enterDATEPART(TSqlParser.DATEPARTContext ctx) {

    }

    @Override
    public void exitDATEPART(TSqlParser.DATEPARTContext ctx) {

    }

    @Override
    public void enterGETDATE(TSqlParser.GETDATEContext ctx) {

    }

    @Override
    public void exitGETDATE(TSqlParser.GETDATEContext ctx) {

    }

    @Override
    public void enterGETUTCDATE(TSqlParser.GETUTCDATEContext ctx) {

    }

    @Override
    public void exitGETUTCDATE(TSqlParser.GETUTCDATEContext ctx) {

    }

    @Override
    public void enterIDENTITY(TSqlParser.IDENTITYContext ctx) {

    }

    @Override
    public void exitIDENTITY(TSqlParser.IDENTITYContext ctx) {

    }

    @Override
    public void enterMIN_ACTIVE_ROWVERSION(TSqlParser.MIN_ACTIVE_ROWVERSIONContext ctx) {

    }

    @Override
    public void exitMIN_ACTIVE_ROWVERSION(TSqlParser.MIN_ACTIVE_ROWVERSIONContext ctx) {

    }

    @Override
    public void enterNULLIF(TSqlParser.NULLIFContext ctx) {

    }

    @Override
    public void exitNULLIF(TSqlParser.NULLIFContext ctx) {

    }

    @Override
    public void enterSTUFF(TSqlParser.STUFFContext ctx) {

    }

    @Override
    public void exitSTUFF(TSqlParser.STUFFContext ctx) {

    }

    @Override
    public void enterSESSION_USER(TSqlParser.SESSION_USERContext ctx) {

    }

    @Override
    public void exitSESSION_USER(TSqlParser.SESSION_USERContext ctx) {

    }

    @Override
    public void enterSYSTEM_USER(TSqlParser.SYSTEM_USERContext ctx) {

    }

    @Override
    public void exitSYSTEM_USER(TSqlParser.SYSTEM_USERContext ctx) {

    }

    @Override
    public void enterISNULL(TSqlParser.ISNULLContext ctx) {

    }

    @Override
    public void exitISNULL(TSqlParser.ISNULLContext ctx) {

    }

    @Override
    public void enterXML_DATA_TYPE_FUNC(TSqlParser.XML_DATA_TYPE_FUNCContext ctx) {

    }

    @Override
    public void exitXML_DATA_TYPE_FUNC(TSqlParser.XML_DATA_TYPE_FUNCContext ctx) {

    }

    @Override
    public void enterXml_data_type_methods(TSqlParser.Xml_data_type_methodsContext ctx) {

    }

    @Override
    public void exitXml_data_type_methods(TSqlParser.Xml_data_type_methodsContext ctx) {

    }

    @Override
    public void enterValue_method(TSqlParser.Value_methodContext ctx) {

    }

    @Override
    public void exitValue_method(TSqlParser.Value_methodContext ctx) {

    }

    @Override
    public void enterQuery_method(TSqlParser.Query_methodContext ctx) {

    }

    @Override
    public void exitQuery_method(TSqlParser.Query_methodContext ctx) {

    }

    @Override
    public void enterExist_method(TSqlParser.Exist_methodContext ctx) {

    }

    @Override
    public void exitExist_method(TSqlParser.Exist_methodContext ctx) {

    }

    @Override
    public void enterModify_method(TSqlParser.Modify_methodContext ctx) {

    }

    @Override
    public void exitModify_method(TSqlParser.Modify_methodContext ctx) {

    }

    @Override
    public void enterNodes_method(TSqlParser.Nodes_methodContext ctx) {

    }

    @Override
    public void exitNodes_method(TSqlParser.Nodes_methodContext ctx) {

    }

    @Override
    public void enterSwitch_section(TSqlParser.Switch_sectionContext ctx) {

    }

    @Override
    public void exitSwitch_section(TSqlParser.Switch_sectionContext ctx) {

    }

    @Override
    public void enterSwitch_search_condition_section(TSqlParser.Switch_search_condition_sectionContext ctx) {

    }

    @Override
    public void exitSwitch_search_condition_section(TSqlParser.Switch_search_condition_sectionContext ctx) {

    }

    @Override
    public void enterAs_column_alias(TSqlParser.As_column_aliasContext ctx) {

    }

    @Override
    public void exitAs_column_alias(TSqlParser.As_column_aliasContext ctx) {

    }

    @Override
    public void enterAs_table_alias(TSqlParser.As_table_aliasContext ctx) {

    }

    @Override
    public void exitAs_table_alias(TSqlParser.As_table_aliasContext ctx) {

    }

    @Override
    public void enterTable_alias(TSqlParser.Table_aliasContext ctx) {

    }

    @Override
    public void exitTable_alias(TSqlParser.Table_aliasContext ctx) {

    }

    @Override
    public void enterWith_table_hints(TSqlParser.With_table_hintsContext ctx) {

    }

    @Override
    public void exitWith_table_hints(TSqlParser.With_table_hintsContext ctx) {

    }

    @Override
    public void enterInsert_with_table_hints(TSqlParser.Insert_with_table_hintsContext ctx) {

    }

    @Override
    public void exitInsert_with_table_hints(TSqlParser.Insert_with_table_hintsContext ctx) {

    }

    @Override
    public void enterTable_hint(TSqlParser.Table_hintContext ctx) {

    }

    @Override
    public void exitTable_hint(TSqlParser.Table_hintContext ctx) {

    }

    @Override
    public void enterIndex_value(TSqlParser.Index_valueContext ctx) {

    }

    @Override
    public void exitIndex_value(TSqlParser.Index_valueContext ctx) {

    }

    @Override
    public void enterColumn_alias_list(TSqlParser.Column_alias_listContext ctx) {

    }

    @Override
    public void exitColumn_alias_list(TSqlParser.Column_alias_listContext ctx) {

    }

    @Override
    public void enterColumn_alias(TSqlParser.Column_aliasContext ctx) {

    }

    @Override
    public void exitColumn_alias(TSqlParser.Column_aliasContext ctx) {

    }

    @Override
    public void enterTable_value_constructor(TSqlParser.Table_value_constructorContext ctx) {

    }

    @Override
    public void exitTable_value_constructor(TSqlParser.Table_value_constructorContext ctx) {

    }

    @Override
    public void enterExpression_list(TSqlParser.Expression_listContext ctx) {

    }

    @Override
    public void exitExpression_list(TSqlParser.Expression_listContext ctx) {

    }

    @Override
    public void enterRanking_windowed_function(TSqlParser.Ranking_windowed_functionContext ctx) {

    }

    @Override
    public void exitRanking_windowed_function(TSqlParser.Ranking_windowed_functionContext ctx) {

    }

    @Override
    public void enterAggregate_windowed_function(TSqlParser.Aggregate_windowed_functionContext ctx) {

    }

    @Override
    public void exitAggregate_windowed_function(TSqlParser.Aggregate_windowed_functionContext ctx) {

    }

    @Override
    public void enterAnalytic_windowed_function(TSqlParser.Analytic_windowed_functionContext ctx) {

    }

    @Override
    public void exitAnalytic_windowed_function(TSqlParser.Analytic_windowed_functionContext ctx) {

    }

    @Override
    public void enterAll_distinct_expression(TSqlParser.All_distinct_expressionContext ctx) {

    }

    @Override
    public void exitAll_distinct_expression(TSqlParser.All_distinct_expressionContext ctx) {

    }

    @Override
    public void enterOver_clause(TSqlParser.Over_clauseContext ctx) {

    }

    @Override
    public void exitOver_clause(TSqlParser.Over_clauseContext ctx) {

    }

    @Override
    public void enterRow_or_range_clause(TSqlParser.Row_or_range_clauseContext ctx) {

    }

    @Override
    public void exitRow_or_range_clause(TSqlParser.Row_or_range_clauseContext ctx) {

    }

    @Override
    public void enterWindow_frame_extent(TSqlParser.Window_frame_extentContext ctx) {

    }

    @Override
    public void exitWindow_frame_extent(TSqlParser.Window_frame_extentContext ctx) {

    }

    @Override
    public void enterWindow_frame_bound(TSqlParser.Window_frame_boundContext ctx) {

    }

    @Override
    public void exitWindow_frame_bound(TSqlParser.Window_frame_boundContext ctx) {

    }

    @Override
    public void enterWindow_frame_preceding(TSqlParser.Window_frame_precedingContext ctx) {

    }

    @Override
    public void exitWindow_frame_preceding(TSqlParser.Window_frame_precedingContext ctx) {

    }

    @Override
    public void enterWindow_frame_following(TSqlParser.Window_frame_followingContext ctx) {

    }

    @Override
    public void exitWindow_frame_following(TSqlParser.Window_frame_followingContext ctx) {

    }

    @Override
    public void enterCreate_database_option(TSqlParser.Create_database_optionContext ctx) {

    }

    @Override
    public void exitCreate_database_option(TSqlParser.Create_database_optionContext ctx) {

    }

    @Override
    public void enterDatabase_filestream_option(TSqlParser.Database_filestream_optionContext ctx) {

    }

    @Override
    public void exitDatabase_filestream_option(TSqlParser.Database_filestream_optionContext ctx) {

    }

    @Override
    public void enterDatabase_file_spec(TSqlParser.Database_file_specContext ctx) {

    }

    @Override
    public void exitDatabase_file_spec(TSqlParser.Database_file_specContext ctx) {

    }

    @Override
    public void enterFile_group(TSqlParser.File_groupContext ctx) {

    }

    @Override
    public void exitFile_group(TSqlParser.File_groupContext ctx) {

    }

    @Override
    public void enterFile_spec(TSqlParser.File_specContext ctx) {

    }

    @Override
    public void exitFile_spec(TSqlParser.File_specContext ctx) {

    }

    @Override
    public void enterEntity_name(TSqlParser.Entity_nameContext ctx) {

    }

    @Override
    public void exitEntity_name(TSqlParser.Entity_nameContext ctx) {

    }

    @Override
    public void enterEntity_name_for_azure_dw(TSqlParser.Entity_name_for_azure_dwContext ctx) {

    }

    @Override
    public void exitEntity_name_for_azure_dw(TSqlParser.Entity_name_for_azure_dwContext ctx) {

    }

    @Override
    public void enterEntity_name_for_parallel_dw(TSqlParser.Entity_name_for_parallel_dwContext ctx) {

    }

    @Override
    public void exitEntity_name_for_parallel_dw(TSqlParser.Entity_name_for_parallel_dwContext ctx) {

    }

    @Override
    public void enterFull_table_name(TSqlParser.Full_table_nameContext ctx) {

    }

    @Override
    public void exitFull_table_name(TSqlParser.Full_table_nameContext ctx) {

    }

    @Override
    public void enterTable_name(TSqlParser.Table_nameContext ctx) {

    }

    @Override
    public void exitTable_name(TSqlParser.Table_nameContext ctx) {

    }

    @Override
    public void enterSimple_name(TSqlParser.Simple_nameContext ctx) {

    }

    @Override
    public void exitSimple_name(TSqlParser.Simple_nameContext ctx) {

    }

    @Override
    public void enterFunc_proc_name(TSqlParser.Func_proc_nameContext ctx) {

    }

    @Override
    public void exitFunc_proc_name(TSqlParser.Func_proc_nameContext ctx) {

    }

    @Override
    public void enterDdl_object(TSqlParser.Ddl_objectContext ctx) {

    }

    @Override
    public void exitDdl_object(TSqlParser.Ddl_objectContext ctx) {

    }

    @Override
    public void enterFull_column_name(TSqlParser.Full_column_nameContext ctx) {

    }

    @Override
    public void exitFull_column_name(TSqlParser.Full_column_nameContext ctx) {

    }

    @Override
    public void enterColumn_name_list_with_order(TSqlParser.Column_name_list_with_orderContext ctx) {

    }

    @Override
    public void exitColumn_name_list_with_order(TSqlParser.Column_name_list_with_orderContext ctx) {

    }

    @Override
    public void enterColumn_name_list(TSqlParser.Column_name_listContext ctx) {

    }

    @Override
    public void exitColumn_name_list(TSqlParser.Column_name_listContext ctx) {

    }

    @Override
    public void enterCursor_name(TSqlParser.Cursor_nameContext ctx) {

    }

    @Override
    public void exitCursor_name(TSqlParser.Cursor_nameContext ctx) {

    }

    @Override
    public void enterOn_off(TSqlParser.On_offContext ctx) {

    }

    @Override
    public void exitOn_off(TSqlParser.On_offContext ctx) {

    }

    @Override
    public void enterClustered(TSqlParser.ClusteredContext ctx) {

    }

    @Override
    public void exitClustered(TSqlParser.ClusteredContext ctx) {

    }

    @Override
    public void enterNull_notnull(TSqlParser.Null_notnullContext ctx) {

    }

    @Override
    public void exitNull_notnull(TSqlParser.Null_notnullContext ctx) {

    }

    @Override
    public void enterNull_or_default(TSqlParser.Null_or_defaultContext ctx) {

    }

    @Override
    public void exitNull_or_default(TSqlParser.Null_or_defaultContext ctx) {

    }

    @Override
    public void enterScalar_function_name(TSqlParser.Scalar_function_nameContext ctx) {

    }

    @Override
    public void exitScalar_function_name(TSqlParser.Scalar_function_nameContext ctx) {

    }

    @Override
    public void enterBegin_conversation_timer(TSqlParser.Begin_conversation_timerContext ctx) {

    }

    @Override
    public void exitBegin_conversation_timer(TSqlParser.Begin_conversation_timerContext ctx) {

    }

    @Override
    public void enterBegin_conversation_dialog(TSqlParser.Begin_conversation_dialogContext ctx) {

    }

    @Override
    public void exitBegin_conversation_dialog(TSqlParser.Begin_conversation_dialogContext ctx) {

    }

    @Override
    public void enterContract_name(TSqlParser.Contract_nameContext ctx) {

    }

    @Override
    public void exitContract_name(TSqlParser.Contract_nameContext ctx) {

    }

    @Override
    public void enterService_name(TSqlParser.Service_nameContext ctx) {

    }

    @Override
    public void exitService_name(TSqlParser.Service_nameContext ctx) {

    }

    @Override
    public void enterEnd_conversation(TSqlParser.End_conversationContext ctx) {

    }

    @Override
    public void exitEnd_conversation(TSqlParser.End_conversationContext ctx) {

    }

    @Override
    public void enterWaitfor_conversation(TSqlParser.Waitfor_conversationContext ctx) {

    }

    @Override
    public void exitWaitfor_conversation(TSqlParser.Waitfor_conversationContext ctx) {

    }

    @Override
    public void enterGet_conversation(TSqlParser.Get_conversationContext ctx) {

    }

    @Override
    public void exitGet_conversation(TSqlParser.Get_conversationContext ctx) {

    }

    @Override
    public void enterQueue_id(TSqlParser.Queue_idContext ctx) {

    }

    @Override
    public void exitQueue_id(TSqlParser.Queue_idContext ctx) {

    }

    @Override
    public void enterSend_conversation(TSqlParser.Send_conversationContext ctx) {

    }

    @Override
    public void exitSend_conversation(TSqlParser.Send_conversationContext ctx) {

    }

    @Override
    public void enterData_type(TSqlParser.Data_typeContext ctx) {

    }

    @Override
    public void exitData_type(TSqlParser.Data_typeContext ctx) {

    }

    @Override
    public void enterDefault_value(TSqlParser.Default_valueContext ctx) {

    }

    @Override
    public void exitDefault_value(TSqlParser.Default_valueContext ctx) {

    }

    @Override
    public void enterConstant(TSqlParser.ConstantContext ctx) {

    }

    @Override
    public void exitConstant(TSqlParser.ConstantContext ctx) {

    }

    @Override
    public void enterSign(TSqlParser.SignContext ctx) {

    }

    @Override
    public void exitSign(TSqlParser.SignContext ctx) {

    }

    @Override
    public void enterId(TSqlParser.IdContext ctx) {

    }

    @Override
    public void exitId(TSqlParser.IdContext ctx) {

    }

    @Override
    public void enterSimple_id(TSqlParser.Simple_idContext ctx) {

    }

    @Override
    public void exitSimple_id(TSqlParser.Simple_idContext ctx) {

    }

    @Override
    public void enterComparison_operator(TSqlParser.Comparison_operatorContext ctx) {

    }

    @Override
    public void exitComparison_operator(TSqlParser.Comparison_operatorContext ctx) {

    }

    @Override
    public void enterAssignment_operator(TSqlParser.Assignment_operatorContext ctx) {

    }

    @Override
    public void exitAssignment_operator(TSqlParser.Assignment_operatorContext ctx) {

    }

    @Override
    public void enterFile_size(TSqlParser.File_sizeContext ctx) {

    }

    @Override
    public void exitFile_size(TSqlParser.File_sizeContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}
