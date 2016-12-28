package com.jsjrobotics.defaultTemplate.lifecycle.functional;

public interface BiReceiver<FirstArgument, SecondArgument> {
   void accept(FirstArgument t, SecondArgument r);
}
