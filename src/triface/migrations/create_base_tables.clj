(in-ns 'triface.migration)

(defn create-migration-table []
  (db/create-table
   :migration
   [:id "SERIAL" "PRIMARY KEY"]
   [:name "varchar(55)" "NOT NULL" "UNIQUE"]
   [:run_at "timestamp with time zone" "NOT NULL" "DEFAULT current_timestamp"]))

(defn create-model-table []
  (db/create-table
   :model
   [:id "SERIAL" "PRIMARY KEY"]
   [:name "varchar(55)" "NOT NULL" "UNIQUE"]
   [:description :text "DEFAULT ''"]
   [:position :integer "DEFAULT 0"]
   [:nested :boolean "DEFAULT false"]
   [:locked :boolean "DEFAULT false"]
   [:abstract :boolean "DEFAULT false"]
   [:ancestor_id :integer "DEFAULT 0"]
   [:created_at "timestamp with time zone" "NOT NULL" "DEFAULT current_timestamp"]
   [:updated_at "timestamp with time zone" "NOT NULL" "DEFAULT current_timestamp"]))

(defn create-field-table []
  (db/create-table
   :field
   [:id "SERIAL" "PRIMARY KEY"]
   [:model_id :integer "DEFAULT 0"]
   [:link_id :integer "DEFAULT 0"]
   [:name "varchar(55)" "NOT NULL"]
   [:type "varchar(256)" "NOT NULL"]
   [:description :text "DEFAULT ''"]
   [:position :integer "DEFAULT 0"]
   [:required :boolean "DEFAULT false"]
   [:disjoint :boolean "DEFAULT false"]
   [:singular :boolean "DEFAULT false"]
   [:locked :boolean "DEFAULT false"]
   [:created_at "timestamp with time zone" "NOT NULL" "DEFAULT current_timestamp"]
   [:updated_at "timestamp with time zone" "NOT NULL" "DEFAULT current_timestamp"]))

(def migrate (fn []
  (create-migration-table)
  (create-model-table)
  (create-field-table)))

