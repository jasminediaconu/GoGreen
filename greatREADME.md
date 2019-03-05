#How To Write a great README,  There is a great readme (0.1) - RUBRIC

## 1. Write an Example, Right Now
The very first thing that should be written in a readme is a short example code snippet. This snippet should show what your library does, and as little else as possible. In the ideal case, people should be able to infer from your example how your library works.

For example, here’s a stubbed-out readme for a small utility by SubStack:

mkdirp
Like mkdir -p, but in node.js!

Example
pow.js
var mkdirp = require('mkdirp');
 mkdirp('/tmp/foo/bar/baz', 0755, function (err) {
    if (err) console.error(err)
    else console.log('pow!')
});
Output pow!

And now /tmp/foo/bar/baz exists, huzzah!

This probably isn’t the greatest readme of all time. However, the example is a perfect case study. In four lines, a programmer more or less fluent in javascript can tell that mkdirp:

Is a function.
Takes 3 arguments:
file path
octal permissions
An errorback
Probably makes directories.
Add in the mention of “like mkdir -p” and, without any other documentation, we can tell how it works.

Of course, mkdir -p is small enough that you can actually cover 100% of the API in one example. Not all projects are so lucky, but they can still strive to get a common use case across.

Here’s another good example of an example, from node-tap. The example in this code saves what would otherwise be a terrible readme, to the point where node-tap has a solid contingent of loyal users despite its readme.

## 2. Describe the install procedure.
A project that I think does a really good job of this is hook.io:

Getting Start / Demo
 npm install hook.io-helloworld -g
Now run:

 hookio-helloworld
Spawn up as many as you want. The first one becomes a server, the rest will become clients. Each helloworld hook emits a hello on an interval. Now watch the i/o party go!

Two. Lines. You now know how to get up and going with hook.io!

Node.js projects like hook.io are lucky in this regard, as npm makes most installations for node projects a one-liner. This is ideal, as ease of installation is another selling point for your project.

If you’re not lucky enough to have an easy installation procedure, then this section becomes even more important. In the case of hook.io, I may have been able to put 2 and 2 together and went looking for it on npm. For a more complicated yet poorly-documented install procedure, developers will screw it up, at which point they will either complain to you or give up and move on.

Why?
Assuming your example made a sale, the second question a developer is going to ask is, “How do I install this thing?” Make it easy for them.

## 3. Stub out the API docs.
Now that you have a basic example and installation instructions, you can move on to documenting the basic API.

When I write API docs (and this is admittedly a weak point for me), I like to start with obvious entry points (your module only has one obvious entry point, right?) and work from there.

For example, here’s a snippet from some API docs I wrote for union:

union.createServer(options)
The options object is required. Options include:

options.before
options.before is an array of middlewares, which are used to route and serve incoming requests. For instance, in the example, favicon is a middleware which handles requests for /favicon.ico.

Union’s request handling is connect-compatible, meaning that all existing connect middlewares should work out-of-the-box with union.

In addition, the response object passed to middlewares listens for a “next” event, which is equivalent to calling next(). Flatiron middlewares are written in this manner, meaning they are not reverse-compatible with connect.

options.after
options.after is an array of stream filters, which are applied after the request handlers in options.before. Stream filters inherit from union.ResponseStream, which implements the Node.js core streams api with a bunch of other goodies.

The advantage to streaming middlewares is that they do not require buffering the entire stream in order to execute their function.

options.limit (optional)
This argument is passed to internal instantiations of union.BufferedStream.

In union, the obvious entry point is union.createServer. In this example, I tried to explain what you do with it, and what all the pieces mean.

Why?
Hopefully the idea of documenting your code isn’t completely foreign. That said, you may be tempted to think that an example is “enough”. I would suggest that this is true only in very rare cases. Even mkdirp could use a short paragraph explaining the arguments and behavior of the module. Inferrence is great, but shouldn’t be completely relied upon. Be straightforward.

## 4. Tests
Your module may or may not have tests. If it does have tests (and tests are a wonderful idea!), you should describe how to run them. For example, here’s a snippet that’s very familiar to us at Nodejitsu:

Run Tests
Tests are written in vows and give complete coverage of all APIs and storage engines.

$ npm test
This is pretty much ideal.

Why?
Like installation instructions, testing directions should be as straightforward as possible. If you’re using node and npm, npm test is great because it distills testing into a one-liner.

(Choosing the right testing framework is another discussion entirely. I personally like node-tap).

## 5. Licensing and Contributors
Finally, tag on your license and contributors.

The content of this isn’t actually too important in the context of a readme. For example, I usually just write the following:

License:
MIT/X11.