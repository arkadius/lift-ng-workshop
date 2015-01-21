# lift-ng-workshop

This project is for purpose of workshop about lift-ng. If yout want to see whole progress of presention, checkout tags in name order.<br>
The project is based on [lift_26_sbt](https://github.com/lift/lift_26_sbt/tree/master/scala_211/lift_basic)

## Run

To start container just run:
```bash
./sbt
> container:start
```

To watch for changes in files and reload after classes after them, inside sbt run:
```bash
> ~; compile; container:reload /
```

After dependencies change you must stop watching for changes of files and than restart container:
```bash
> container:stop
> reload
> container:start
```

## License

lift-ng-workshop is released under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).
