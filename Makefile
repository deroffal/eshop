include api-tests/Makefile applications/Makefile observability/Makefile

test-local:
	@make -C api-tests run-api-tests;

run-local:
	@make -C applications run-docker;
