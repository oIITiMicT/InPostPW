CREATE TABLE "users"(
                        "id" INTEGER NOT NULL,
                        "username" VARCHAR(255) NOT NULL,
                        "password_hash" VARCHAR(255) NOT NULL,
                        "email" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "users" ADD PRIMARY KEY("id");
ALTER TABLE
    "users" ADD CONSTRAINT "users_username_unique" UNIQUE("username");
ALTER TABLE
    "users" ADD CONSTRAINT "users_email_unique" UNIQUE("email");
CREATE TABLE "packages"(
                           "id" BIGINT NOT NULL,
                           "sender_id" BIGINT NOT NULL,
                           "recipient_id" BIGINT NOT NULL,
                           "tracker" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "packages" ADD PRIMARY KEY("id");
ALTER TABLE
    "packages" ADD CONSTRAINT "packages_tracker_unique" UNIQUE("tracker");
CREATE TABLE "stages"(
                         "id" BIGINT NOT NULL,
                         "package_id" BIGINT NOT NULL,
                         "description" VARCHAR(255) NOT NULL,
                         "time" TIME(0) WITHOUT TIME ZONE NOT NULL
);
ALTER TABLE
    "stages" ADD PRIMARY KEY("id");
CREATE TABLE "users_roles"(
                              "user_id" BIGINT NOT NULL,
                              "role_id" BIGINT NOT NULL
);
CREATE TABLE "roles"(
                        "id" BIGINT NOT NULL,
                        "name" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "roles" ADD PRIMARY KEY("id");
CREATE TABLE "roles_permissions"(
                                    "role_id" BIGINT NOT NULL,
                                    "permission_id" BIGINT NOT NULL
);
CREATE TABLE "permissions"(
                              "id" BIGINT NOT NULL,
                              "name" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "permissions" ADD PRIMARY KEY("id");
ALTER TABLE
    "packages" ADD CONSTRAINT "packages_sender_id_foreign" FOREIGN KEY("sender_id") REFERENCES "users"("id");
ALTER TABLE
    "packages" ADD CONSTRAINT "packages_recipient_id_foreign" FOREIGN KEY("recipient_id") REFERENCES "users"("id");
ALTER TABLE
    "stages" ADD CONSTRAINT "stages_package_id_foreign" FOREIGN KEY("package_id") REFERENCES "packages"("id");
ALTER TABLE
    "roles_permissions" ADD CONSTRAINT "roles_permissions_permission_id_foreign" FOREIGN KEY("permission_id") REFERENCES "permissions"("id");
ALTER TABLE
    "users_roles" ADD CONSTRAINT "users_roles_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "users"("id");
ALTER TABLE
    "users_roles" ADD CONSTRAINT "users_roles_role_id_foreign" FOREIGN KEY("role_id") REFERENCES "roles"("id");
ALTER TABLE
    "roles_permissions" ADD CONSTRAINT "roles_permissions_role_id_foreign" FOREIGN KEY("role_id") REFERENCES "roles"("id");