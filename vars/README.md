```
(root)
+- vars
|   +- foo.groovy          # for global 'foo' variable
|   +- foo.txt             # help for 'foo' variable
```

https://jenkins.io/doc/book/pipeline/shared-libraries/#defining-custom-steps

Shared Libraries can also define global variables which behave similarly to built-in steps, such as sh or git. Global variables defined in Shared Libraries must be named with all lower-case or "camelCased" in order to be loaded properly by Pipeline.
