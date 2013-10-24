(ns angstrom.ajax)

(defn request
  [method url handler]
  (let [xhr (new js/XMLHttpRequest)]
    (set! 
     (.-onreadystatechange xhr) 
     (fn [] 
       (if (= 4 (.-readyState xhr)) 
         (handler (.-responseText xhr)))))
    (.open xhr method url true)
    (.send xhr nil)))

(defn GET
  [url handler]
  (request "GET" url handler))
