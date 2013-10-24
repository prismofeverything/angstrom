(defproject angstrom "0.0.1"
  :description "generic clojurescript project"
  :repositories {"sonatype-oss-public" "https://oss.sonatype.org/content/groups/public/"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-1909"]
                 [org.clojars.magomimmo/core.async "0.1.0-SNAPSHOT"]
                 [domina "1.0.2-SNAPSHOT"]
                 [ring "1.2.0"]
                 [org.clojure/google-closure-library-third-party "0.0-2029"]]
  :plugins [[lein-ring "0.8.6"]
            [lein-cljsbuild "0.3.3"]]
  :jvm-opts ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=n" "-Xmx2g"]
  :source-paths ["src/clj"]
  :resource-paths ["resources/"]
  :min-lein-version "2.0.0"
  :main angstrom.core
  :ring {:handler angstrom.core/handler
         :init angstrom.core/init
         :port 12321
         :auto-reload? false
         :servlet-name "angstrom-frontend"}
  :cljsbuild {
    :builds {
      :dev {
        :source-paths ["src/cljs"]  
        :compiler {
          :optimizations :whitespace
          :output-to  "resources/public/js/app/angstrom.js"
          :output-dir "resources/public/js/app/out"}}}})
